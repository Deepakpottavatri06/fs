import { AppBar, Toolbar, Typography, Button } from '@mui/material';
import { Link } from 'react-router-dom';

export default function Navbar() {
  return (
    <AppBar position="static" color="primary">
      <Toolbar sx={{ display: 'flex', justifyContent: 'space-between' }}>
        <Typography variant="h6">Profile Manager</Typography>
        <div>
          <Button color="inherit" component={Link} to="/">New Profile</Button>
          <Button color="inherit" component={Link} to="/profiles">All Profiles</Button>
        </div>
      </Toolbar>
    </AppBar>
  );
}
