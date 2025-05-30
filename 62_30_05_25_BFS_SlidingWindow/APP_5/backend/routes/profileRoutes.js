const express = require('express');
const router = express.Router();
const Profile = require('../models/Profile');

// Create Profile
router.post('/', async (req, res) => {
  try {
    const data = {
      ...req.body,
      interests: req.body.interests?.split(',').map(s => s.trim()),
      achievements: req.body.achievements?.split(',').map(s => s.trim()),
    };
    const newProfile = new Profile(data);
    await newProfile.save();
    res.status(201).json(newProfile);
  } catch (err) {
    res.status(500).json({ message: 'Error creating profile', error: err.message });
  }
});


module.exports = router;
