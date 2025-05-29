import React, { useEffect, useState } from 'react';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Button, Toolbar, Typography, TablePagination } from '@mui/material';
import AddIcon from '@mui/icons-material/Add';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

function InvoiceList() {
  const [invoices, setInvoices] = useState([]);
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(5);
  const navigate = useNavigate();

  useEffect(() => {
    axios.get('/invoices').then(res => setInvoices(res.data));
  }, []);

  return (
    <Paper>
      <Toolbar sx={{ justifyContent: 'space-between' }}>
        <Typography variant="h6">Invoices</Typography>
        <Button variant="contained" startIcon={<AddIcon />} onClick={() => navigate('/invoices/new')}>Add</Button>
      </Toolbar>
      <TableContainer>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Invoice #</TableCell>
              <TableCell>Date</TableCell>
              <TableCell>Customer</TableCell>
              <TableCell>Total Amount</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {invoices.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage).map((inv) => (
              <TableRow key={inv._id} hover onClick={() => navigate(`/invoices/${inv._id}`)} style={{ cursor: 'pointer' }}>
                <TableCell>{inv.invoiceNumber}</TableCell>
                <TableCell>{inv.date}</TableCell>
                <TableCell>{inv.customerName}</TableCell>
                <TableCell>
                  {typeof inv.totalAmount === 'object' && inv.totalAmount !== null && '$numberDecimal' in inv.totalAmount
                    ? inv.totalAmount.$numberDecimal
                    : inv.totalAmount}
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
      <TablePagination
        component="div"
        count={invoices.length}
        page={page}
        onPageChange={(_, newPage) => setPage(newPage)}
        rowsPerPage={rowsPerPage}
        onRowsPerPageChange={e => { setRowsPerPage(parseInt(e.target.value, 10)); setPage(0); }}
      />
    </Paper>
  );
}

export default InvoiceList;
