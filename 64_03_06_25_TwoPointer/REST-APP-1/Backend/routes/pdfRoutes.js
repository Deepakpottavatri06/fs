const express = require("express");
const multer = require("multer");
const path = require("path");
const { authenticateJWT, authorizeRoles } = require("../middleware/authMiddleware");
const { uploadPdf } = require("../controllers/pdfController");

const router = express.Router();

// Multer config
const storage = multer.diskStorage({
  destination: function (req, file, cb) {
    cb(null, "uploads/");
  },
  filename: function (req, file, cb) {
    const uniqueSuffix = Date.now() + "-" + Math.round(Math.random() * 1E9);
    cb(null, uniqueSuffix + path.extname(file.originalname));
  }
});

const upload = multer({ storage: storage });

// Admin-only route to upload PDF
router.post(
  "/upload",
//   authenticateJWT,
//   authorizeRoles("admin"),
  upload.single("pdf"),
  uploadPdf
);

module.exports = router;
