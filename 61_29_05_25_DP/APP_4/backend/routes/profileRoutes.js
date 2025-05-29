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

// Get All Profiles
router.get('/', async (req, res) => {
  try {
    const profiles = await Profile.find();
    res.status(200).json(profiles);
  } catch (err) {
    res.status(500).json({ message: 'Error fetching profiles', error: err.message });
  }
});

// Get Profile by ID
router.get('/:id', async (req, res) => {
  try {
    const profile = await Profile.findById(req.params.id);
    if (!profile) return res.status(404).json({ message: 'Profile not found' });
    res.status(200).json(profile);
  } catch (err) {
    res.status(500).json({ message: 'Error fetching profile', error: err.message });
  }
});

module.exports = router;
