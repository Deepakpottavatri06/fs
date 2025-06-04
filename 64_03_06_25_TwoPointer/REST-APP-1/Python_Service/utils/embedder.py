import os
import json
import uuid
import numpy as np
import faiss
from sentence_transformers import SentenceTransformer
from langchain.text_splitter import RecursiveCharacterTextSplitter   # NEW

# ────────────────────────────────────────────────────────────────────
# Model & paths
# ────────────────────────────────────────────────────────────────────
model = SentenceTransformer("all-MiniLM-L6-v2")
VECTOR_STORE_PATH = "indexes/vector_store.faiss"
CHUNKS_META_PATH   = "indexes/vector_store_meta.json"   # keeps chunk text → vector id mapping

# ────────────────────────────────────────────────────────────────────
# Helpers
# ────────────────────────────────────────────────────────────────────
def chunk_text(text: str,
               chunk_size: int = 512,
               chunk_overlap: int = 100) -> list[str]:
    """Split `text` with recursive splitter (hierarchy: \n → . → space)."""
    splitter = RecursiveCharacterTextSplitter(
        chunk_size=chunk_size,
        chunk_overlap=chunk_overlap,
        separators=["\n\n", "\n", ".", " ", ""],   
    )
    return splitter.split_text(text)

def _load_meta() -> list[str]:
    if os.path.exists(CHUNKS_META_PATH):
        with open(CHUNKS_META_PATH, "r", encoding="utf-8") as f:
            return json.load(f)
    return []

def _save_meta(meta: list[str]) -> None:
    with open(CHUNKS_META_PATH, "w", encoding="utf-8") as f:
        json.dump(meta, f, ensure_ascii=False, indent=2)

# ────────────────────────────────────────────────────────────────────
# Main embedding / indexing functions
# ────────────────────────────────────────────────────────────────────
def embed_and_store(text: str) -> dict:
    """Add embeddings of `text` to the (single) FAISS index."""
    chunks = chunk_text(text)
    embeddings = model.encode(chunks)

    dim = embeddings.shape[1]
    os.makedirs("indexes", exist_ok=True)

    # 1️⃣ load or create the FAISS index
    if os.path.exists(VECTOR_STORE_PATH):
        index = faiss.read_index(VECTOR_STORE_PATH)
    else:
        index = faiss.IndexFlatL2(dim)

    # 2️⃣ add vectors
    index.add(np.array(embeddings, dtype="float32"))
    faiss.write_index(index, VECTOR_STORE_PATH)

    # 3️⃣ persist chunk-text metadata (simple array: idx 0 → first vector)
    meta = _load_meta()
    meta.extend(chunks)
    _save_meta(meta)

    return {
        "message": "Embeddings added to shared vector store.",
        "new_chunks": len(chunks),
        "total_vectors": index.ntotal,
        "vectorStorePath": VECTOR_STORE_PATH,
    }

def embed_question(question: str) -> np.ndarray:
    return model.encode([question])[0].astype("float32")

def search_similar_chunks(question: str, top_k: int = 5) -> list[str]:
    """Return the *text* of the top-k most similar chunks."""
    if not os.path.exists(VECTOR_STORE_PATH):
        raise ValueError("Vector store not found. Upload a PDF first.")

    index  = faiss.read_index(VECTOR_STORE_PATH)
    meta   = _load_meta()            # list[str] – same order as vectors
    q_vec  = embed_question(question).reshape(1, -1)

    D, I = index.search(q_vec, top_k)
    indices = I[0]

    results = [meta[i] for i in indices if 0 <= i < len(meta)]
    return results
