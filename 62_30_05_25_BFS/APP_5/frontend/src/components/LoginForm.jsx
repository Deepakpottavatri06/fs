import React, { useState } from 'react';
import { TextField, Button, Typography, Box, Alert } from '@mui/material';
import axios from 'axios';

function LoginForm({ onLoginSuccess }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleLogin = async () => {
    try {
      const res = await axios.post('/api/auth/login', { username, password });

      // Save token and role to localStorage
      const { token, role } = res.data;
      localStorage.setItem('auth', JSON.stringify({ token, role }));

      // Trigger login success callback
      onLoginSuccess();
    } catch (err) {
      setError(err.response?.data?.message || 'Login failed. Please try again.');
    }
  };

  return (
    <Box sx={{ maxWidth: 400, mx: 'auto', mt: 4 }}>
      <Typography variant="h5" gutterBottom>Login</Typography>

      <TextField
        label="Username"
        fullWidth
        margin="normal"
        value={username}
        onChange={e => setUsername(e.target.value)}
      />

      <TextField
        label="Password"
        type="password"
        fullWidth
        margin="normal"
        value={password}
        onChange={e => setPassword(e.target.value)}
      />

      {error && <Alert severity="error" sx={{ mt: 2 }}>{error}</Alert>}

      <Button variant="contained" fullWidth sx={{ mt: 3 }} onClick={handleLogin}>
        Login
      </Button>
    </Box>
  );
}

export default LoginForm;
