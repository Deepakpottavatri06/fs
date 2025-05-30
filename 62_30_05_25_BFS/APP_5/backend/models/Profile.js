const mongoose = require('mongoose');

const profileSchema = new mongoose.Schema({
  name: { type: String, required: true },
  email: String,
  phone: String,
  degree: String,
  institution: String,
  year: Number,
  interests: [String],
  achievements: [String],
}, { timestamps: true });

module.exports = mongoose.model('Profile', profileSchema);
