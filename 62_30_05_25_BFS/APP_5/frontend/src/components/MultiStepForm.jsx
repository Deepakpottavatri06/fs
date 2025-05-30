import React, { useState } from 'react';
import {
  Box,
  Button,
  Step,
  StepLabel,
  Stepper,
  TextField,
  Typography,
} from '@mui/material';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const steps = ['Personal Info', 'Education', 'Interests', 'Achievements'];

const MultiStepForm = () => {
  const navigate = useNavigate();
  const [step, setStep] = useState(0);
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    phone: '',
    degree: '',
    institution: '',
    year: '',
    interests: '',
    achievements: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const nextStep = () => setStep(prev => prev + 1);
  const prevStep = () => setStep(prev => prev - 1);

  const handleSubmit = async () => {
    try {
      await axios.post('/api/profiles', formData);
      navigate('/profiles');
    } catch (err) {
      console.error('Error submitting profile:', err.message);
    }
  };

  const renderStep = () => {
    switch (step) {
      case 0:
        return (
          <>
            <TextField fullWidth margin="normal" name="name" label="Name" value={formData.name} onChange={handleChange} />
            <TextField fullWidth margin="normal" name="email" label="Email" value={formData.email} onChange={handleChange} />
            <TextField fullWidth margin="normal" name="phone" label="Phone" value={formData.phone} onChange={handleChange} />
          </>
        );
      case 1:
        return (
          <>
            <TextField fullWidth margin="normal" name="degree" label="Degree" value={formData.degree} onChange={handleChange} />
            <TextField fullWidth margin="normal" name="institution" label="Institution" value={formData.institution} onChange={handleChange} />
            <TextField fullWidth margin="normal" name="year" label="Year" type="number" value={formData.year} onChange={handleChange} />
          </>
        );
      case 2:
        return (
          <TextField
            fullWidth
            multiline
            margin="normal"
            name="interests"
            label="Interests (comma-separated)"
            value={formData.interests}
            onChange={handleChange}
          />
        );
      case 3:
        return (
          <TextField
            fullWidth
            multiline
            margin="normal"
            name="achievements"
            label="Achievements (comma-separated)"
            value={formData.achievements}
            onChange={handleChange}
          />
        );
      default:
        return null;
    }
  };

  return (
    <Box>
      <Typography variant="h5" mb={2}>Create Profile</Typography>
      <Stepper activeStep={step} alternativeLabel>
        {steps.map(label => (
          <Step key={label}>
            <StepLabel>{label}</StepLabel>
          </Step>
        ))}
      </Stepper>

      <Box mt={4}>{renderStep()}</Box>

      <Box mt={3} display="flex" justifyContent="space-between">
        <Button disabled={step === 0} onClick={prevStep}>Previous</Button>
        {step < steps.length - 1 ? (
          <Button variant="contained" onClick={nextStep}>Next</Button>
        ) : (
          <Button variant="contained" onClick={handleSubmit} >Submit</Button>
        )}
      </Box>
    </Box>
  );
};

export default MultiStepForm;
