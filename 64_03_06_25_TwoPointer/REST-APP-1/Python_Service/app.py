from flask import Flask, request, jsonify
import os
from werkzeug.utils import secure_filename

from utils.pdf_reader import extract_text_from_pdf
from utils.embedder import embed_and_store, search_similar_chunks

app = Flask(__name__)
UPLOAD_FOLDER = 'uploads'
os.makedirs(UPLOAD_FOLDER, exist_ok=True)

@app.route("/process-pdf", methods=["POST"])
def process_pdf():

    # return jsonify({"message": "PDF processing endpoint"}), 200
    if 'pdf' not in request.files:
        return jsonify({"error": "No PDF file provided"}), 400

    file = request.files['pdf']
    filename = secure_filename(file.filename)
    pdf_path = os.path.join(UPLOAD_FOLDER, filename)
    file.save(pdf_path)

    try:
        text = extract_text_from_pdf(pdf_path)
        result = embed_and_store(text)
        return jsonify({
            "message": "PDF processed successfully",
        }), 200
    except Exception as e:
        print(f"Error processing PDF: {e}")
        return jsonify({"error": str(e)}), 500


INDEX_DIR = "indexes"

@app.route("/query", methods=["POST"])
def query():
    data = request.json
    question = data.get("question")

    if not question:
        return jsonify({"error": "No question provided"}), 400

    try:
        results = search_similar_chunks(question, top_k=5)
        print(f"Search results for question '{question}': {results}")
        return jsonify({
            "chunks": results
        }), 200
    except Exception as e:
        return jsonify({"error": str(e)}), 500


if __name__ == "__main__":
    app.run(port=5001, debug=True)
