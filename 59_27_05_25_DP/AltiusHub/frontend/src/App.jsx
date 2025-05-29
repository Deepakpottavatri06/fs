import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { CssBaseline, Box } from '@mui/material';
import SideNav from './components/SideNav';
import InvoiceList from './components/InvoiceList';
import InvoiceDetail from './components/InvoiceDetail';

function App() {
  return (
    <Router>
      <CssBaseline />
      <Box sx={{ display: 'flex' }}>
        <SideNav />
        <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
          <Routes>
            <Route path="/" element={<Navigate to="/invoices" />} />
            <Route path="/invoices" element={<InvoiceList />} />
            <Route path="/invoices/new" element={<InvoiceDetail mode="create" />} />
            <Route path="/invoices/:id" element={<InvoiceDetail mode="edit" />} />
          </Routes>
        </Box>
      </Box>
    </Router>
  );
}

export default App;