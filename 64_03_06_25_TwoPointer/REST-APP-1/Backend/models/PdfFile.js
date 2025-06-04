const mongoose = require('mongoose');

const pdfFileSchema = new mongoose.Schema({
  filename: { type: String, required: true },
  originalname: { type: String, required: true },
  path: { type: String, required: true },
  size: { type: Number, required: true },
  uploadedBy: { type: mongoose.Schema.Types.ObjectId, ref: 'User', required: true },
  uploadDate: { type: Date, default: Date.now },
  embeddingFilePath: { type: String }, // path to .faiss file
  status: { type: String, enum: ['pending', 'processed', 'error'], default: 'pending' }
});

module.exports = mongoose.model('PdfFile', pdfFileSchema);
