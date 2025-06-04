const axios = require("axios");
const path = require("path");
const fs = require("fs");
const FormData = require("form-data"); // ✅ import the correct FormData

const PdfFile = require("../models/PdfFile");

const uploadPdf = async (req, res) => {
    console.log("Received file:", req.file);

    if (!req.file) {
        return res.status(400).json({ message: "No file uploaded" });
    }

    const pdfPath = req.file.path;

    try {
        // ✅ Proper Node.js FormData setup
        const formData = new FormData();
        formData.append("pdf", fs.createReadStream(pdfPath));
        console.log("going")
        const response = await axios.post("http://localhost:5001/process-pdf", formData, {
            headers: formData.getHeaders(), 
        });
        
        console.log("Response from Python service:", response.data);

        res.status(200).json({
            message: "PDF uploaded and processed successfully",
        });
    } catch (error) {
        console.error("Error uploading to Python service:", error.message);
        res.status(500).json({ message: "Failed to process PDF" });
    }
};

module.exports = {
    uploadPdf,
};
