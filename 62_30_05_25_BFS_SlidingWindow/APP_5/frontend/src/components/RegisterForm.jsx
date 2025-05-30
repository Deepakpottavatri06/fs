// import React, { useState } from 'react';
// import { TextField, Button, Typography, Box } from '@mui/material';
// import axios from 'axios';

// function RegisterForm({ onSwitchToLogin }) {
//   const [username, setUsername] = useState('');
//   const [password, setPassword] = useState('');
//   const [message, setMessage] = useState('');
//   const [error, setError] = useState('');

//   const handleRegister = async () => {
//     try {
//       const res = await axios.post('/api/auth/register', { username, password });
//       setMessage(res.data.message);
//       setError('');
//     } catch (err) {
//       setError(err.response?.data?.message || 'Registration failed');
//       setMessage('');
//     }
//   };

//   return (
//     <Box sx={{ maxWidth: 400, mx: 'auto' }}>
//       <Typography variant="h5" gutterBottom>Register</Typography>
//       <TextField
//         label="Username"
//         fullWidth
//         margin="normal"
//         value={username}
//         onChange={e => setUsername(e.target.value)}
//       />
//       <TextField
//         label="Password"
//         type="password"
//         fullWidth
//         margin="normal"
//         value={password}
//         onChange={e => setPassword(e.target.value)}
//       />
//       {message && <Typography color="success.main">{message}</Typography>}
//       {error && <Typography color="error">{error}</Typography>}
//       <Button variant="contained" fullWidth sx={{ mt: 2 }} onClick={handleRegister}>Register</Button>
//       <Button variant="text" fullWidth onClick={onSwitchToLogin}>Back to Login</Button>
//     </Box>
//   );
// }

// export default RegisterForm;

import React, { useState } from 'react';
import { TextField, Button, MenuItem, Typography, Paper, Box } from '@mui/material';
import axios from 'axios';

const roles = ['admin', 'consumer'];

const RegisterForm = ({onSwitchToLogin}) => {
  const [formData, setFormData] = useState({
    username: '',
    password: '',
    role: 'consumer',
  });

  const [message, setMessage] = useState('');

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleRegister = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post('/api/auth/register', formData);
      setMessage("Registration successful.");
    //   onSwitchToLogin(); // Switch to login after successful registration
    } catch (err) {
      setMessage(`Registration failed: ${err.response?.data?.message || err.message}`);
    }
  };

  return (
    <Paper elevation={3} sx={{ p: 4, maxWidth: 400, mx: 'auto', mt: 4 }}>
      <Typography variant="h5" gutterBottom>
        Register
      </Typography>
      <form onSubmit={handleRegister}>
        <TextField
          label="Username"
          name="username"
          value={formData.username}
          onChange={handleChange}
          fullWidth
          required
          margin="normal"
        />
        <TextField
          label="Password"
          name="password"
          type="password"
          value={formData.password}
          onChange={handleChange}
          fullWidth
          required
          margin="normal"
        />
        <TextField
          label="Role"
          name="role"
          value={formData.role}
          onChange={handleChange}
          select
          fullWidth
          required
          margin="normal"
        >
          {roles.map((role) => (
            <MenuItem key={role} value={role}>
              {role}
            </MenuItem>
          ))}
        </TextField>
        <Button type="submit" variant="contained" color="primary" fullWidth sx={{ mt: 2 }}>
          Register
        </Button>
      </form>
      {message && (
        <Box mt={2}>
          <Typography variant="body2" color="textSecondary">
            {message}
          </Typography>
        </Box>
      )}
    </Paper>
  );
};

export default RegisterForm;

