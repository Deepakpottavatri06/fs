import React, { useEffect, useState } from 'react';
import {
  Card,
  CardContent,
  Typography,
  CircularProgress,
  Box,
} from '@mui/material';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

export default function ProfileList() {
  const [profiles, setProfiles] = useState([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchProfiles = async () => {
      try {
        const res = await axios.get('/api/profiles');
        setProfiles(res.data);
      } catch (err) {
        console.error('Error fetching profiles:', err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchProfiles();
  }, []);

  if (loading) {
    return (
      <Box display="flex" justifyContent="center" mt={5}>
        <CircularProgress />
      </Box>
    );
  }

  return (
    <Box mt={4} maxWidth="600px" mx="auto">
      <Typography variant="h4" gutterBottom>
        All Profiles
      </Typography>

      {profiles.map((profile) => (
        <Box
          key={profile._id}
          mb={2}
          onClick={() => navigate(`/profile/${profile._id}`)}
          sx={{
            cursor: 'pointer',
            '&:hover': { backgroundColor: '#f0f0f0' },
            borderRadius: 1,
            transition: 'background-color 0.2s',
          }}
        >
          <Card elevation={0}>
            <CardContent sx={{ py: 1.5 }}>
              <Typography variant="h6">{profile.name}</Typography>
              <Typography variant="body2" color="textSecondary">
                {profile.email}
              </Typography>
            </CardContent>
          </Card>
        </Box>
      ))}
    </Box>
  );
}
