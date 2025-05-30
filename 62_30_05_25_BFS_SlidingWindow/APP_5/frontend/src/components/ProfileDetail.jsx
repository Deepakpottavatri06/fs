import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import {
  Card,
  CardContent,
  Typography,
  CircularProgress,
  Box,
  List,
  ListItem,
  ListItemText
} from '@mui/material';
import axios from 'axios';

export default function ProfileDetail() {
  const { id } = useParams();
  const [profile, setProfile] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchProfile = async () => {
      try {
        const res = await axios.get(`/api/profiles/${id}`);
        setProfile(res.data);
      } catch (err) {
        console.error('Error fetching profile detail:', err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchProfile();
  }, [id]);

  if (loading) {
    return (
      <Box display="flex" justifyContent="center" mt={5}>
        <CircularProgress />
      </Box>
    );
  }

  if (!profile) {
    return <Typography color="error">Profile not found.</Typography>;
  }

const renderCommaSeparatedList = (data) => {
  if (Array.isArray(data)) {
    return data.map((item, index) => (
      <ListItem key={index} disablePadding>
        <ListItemText primary={`• ${item}`} />
      </ListItem>
    ));
  }

  if (typeof data === 'string') {
    return data.split(',').map((item, index) => (
      <ListItem key={index} disablePadding>
        <ListItemText primary={`• ${item.trim()}`} />
      </ListItem>
    ));
  }

  return <ListItem><ListItemText primary="No data available." /></ListItem>;
};


  return (
    <Box mt={4} maxWidth="600px" mx="auto">
        <Typography variant="h4" gutterBottom>
                Profile of Test
        </Typography>
      <Card>
        <CardContent>
          {/* Personal Info */}
          <Typography variant="h5" gutterBottom>Personal Info</Typography>
          <Typography variant="h6">Name: {profile.name}</Typography>
          <Typography>Email: {profile.email}</Typography>
          <Typography>Phone: {profile.phone}</Typography>

          {/* Education */}
          <Box mt={3}>
            <Typography variant="h5" gutterBottom>Education</Typography>
            <Typography>Degree: {profile.degree}</Typography>
            <Typography>Institution: {profile.institution}</Typography>
            <Typography>Year: {profile.year}</Typography>
          </Box>

          {/* Interests */}
          <Box mt={3}>
            <Typography variant="h5" gutterBottom>Interests</Typography>
            <List dense>
              {renderCommaSeparatedList(profile.interests)}
            </List>
          </Box>

          {/* Achievements */}
          <Box mt={3}>
            <Typography variant="h5" gutterBottom>Achievements</Typography>
            <List dense>
              {renderCommaSeparatedList(profile.achievements)}
            </List>
          </Box>
        </CardContent>
      </Card>
    </Box>
  );
}
