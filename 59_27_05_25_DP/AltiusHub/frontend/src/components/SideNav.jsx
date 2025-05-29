import React from 'react';
import { Drawer, List, ListItem, ListItemIcon, ListItemText, Toolbar } from '@mui/material';
import ReceiptIcon from '@mui/icons-material/Receipt';
import { useNavigate } from 'react-router-dom';

const drawerWidth = 220;

function SideNav() {
  const navigate = useNavigate();
  return (
    <Drawer variant="permanent" sx={{ width: drawerWidth, flexShrink: 0,
      [`& .MuiDrawer-paper`]: { width: drawerWidth, boxSizing: 'border-box' } }}>
      <Toolbar />
      <List>
        <ListItem button={true} onClick={() => navigate('/invoices')}>
          <ListItemIcon><ReceiptIcon /></ListItemIcon>
          <ListItemText primary="Invoices" />
        </ListItem>
      </List>
    </Drawer>
  );
}

export default SideNav;