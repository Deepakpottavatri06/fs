const axios = require("axios");
const { GoogleGenerativeAI } = require("@google/generative-ai");

const genAI = new GoogleGenerativeAI(process.env.GEMINI_API_KEY); // Add your Gemini API key to .env

const askQuestion = async (req, res) => {
    const { question } = req.body;

    if (!question) {
        return res.status(400).json({ message: "Question is required" });
    }

    try {
        // Step 1: Get relevant chunks from your Python service
        const ragResponse = await axios.post("http://localhost:5001/query", { question });
        console.log("RAG response:", ragResponse.data);
        const contextChunks = ragResponse.data.chunks;
        const context = contextChunks.join("\n");

        // Step 2: Use Gemini with context + question
        const model = genAI.getGenerativeModel({ model: "gemini-2.0-flash-lite" });
        const prompt = `Answer the following question using the provided context.\n\nContext:\n${context}\n\nQuestion: ${question}`;

        const result = await model.generateContent(prompt);
        const response = await result.response;
        const answer = response.text();

        res.status(200).json({ answer });
    } catch (error) {
        console.error("Gemini error:", error.message);
        res.status(500).json({ message: "Failed to get answer" });
    }
};

module.exports = {
    askQuestion,
};
