import React, { useEffect, useState } from 'react';
import { TextField, Button, Paper, Typography, Grid, IconButton, Divider } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import AddIcon from '@mui/icons-material/Add';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';

const emptyItem = { itemName: '', quantity: 1, price: 1, amount: 1 };
const emptySundry = { billSundryName: '', amount: 0 };

function InvoiceDetail({ mode }) {
  const { id } = useParams();
  const navigate = useNavigate();
  const [invoice, setInvoice] = useState({
    date: '', customerName: '', billingAddress: '', shippingAddress: '', GSTIN: '',
    items: [{ ...emptyItem }],
    billSundries: [],
    totalAmount: 0
  });
  const [errors, setErrors] = useState({});

  // Fetch for edit mode
  useEffect(() => {
    if (mode === 'edit' && id) {
      axios.get(`/invoices/${id}`).then(res => {
        // Map items and bill sundries, handle Decimal128
        const items = (res.data.items || []).map(item => ({
          ...item,
          quantity: parseInt(item.quantity.$numberDecimal),
          price: parseFloat(item.price.$numberDecimal),
          amount: typeof item.amount === 'object' && item.amount !== null && '$numberDecimal' in item.amount
            ? parseFloat(item.amount.$numberDecimal)
            : parseFloat(item.amount)
        }));
        const billSundries = (res.data.billSundries || []).map(s => ({
          ...s,
          amount: typeof s.amount === 'object' && s.amount !== null && '$numberDecimal' in s.amount
            ? Number(s.amount.$numberDecimal)
            : Number(s.amount)
        }));
        const totalAmount = typeof res.data.totalAmount === 'object' && res.data.totalAmount !== null && '$numberDecimal' in res.data.totalAmount
          ? Number(res.data.totalAmount.$numberDecimal)
          : Number(res.data.totalAmount);

        setInvoice({
          date: res.data.date || '',
          customerName: res.data.customerName || '',
          billingAddress: res.data.billingAddress || '',
          shippingAddress: res.data.shippingAddress || '',
          GSTIN: res.data.GSTIN || '',
          invoiceNumber: res.data.invoiceNumber || '',
          items: items.length ? items : [{ ...emptyItem }],
          billSundries,
          totalAmount
        });
      });
    }
  }, [mode, id]);

  // Handlers
  const recalcTotal = (items, billSundries) => {
    const itemsWithAmount = items.map(item => ({
      ...item,
      amount: Number(item.quantity) * Number(item.price)
    }));
    const itemsTotal = itemsWithAmount.reduce((sum, i) => sum + i.amount, 0);
    const sundryTotal = billSundries.reduce((sum, s) => sum + Number(s.amount), 0);
    return { items: itemsWithAmount, totalAmount: itemsTotal + sundryTotal };
  };

  const handleChange = (field, value) => setInvoice({ ...invoice, [field]: value });

  const handleItemChange = (idx, field, value) => {
    const items = invoice.items.map((item, i) =>
      i === idx ? { ...item, [field]: value } : item
    );
    const { items: itemsWithAmount, totalAmount } = recalcTotal(items, invoice.billSundries);
    setInvoice({ ...invoice, items: itemsWithAmount, totalAmount });
  };

  const handleSundryChange = (idx, field, value) => {
    const billSundries = invoice.billSundries.map((s, i) =>
      i === idx ? { ...s, [field]: value } : s
    );
    const { items: itemsWithAmount, totalAmount } = recalcTotal(invoice.items, billSundries);
    setInvoice({ ...invoice, billSundries, items: itemsWithAmount, totalAmount });
  };

  const handleAddItem = () => {
  const items = [...invoice.items, { ...emptyItem }];
  const { items: itemsWithAmount, totalAmount } = recalcTotal(items, invoice.billSundries);
  setInvoice({ ...invoice, items: itemsWithAmount, totalAmount });
};

const handleRemoveItem = (idx) => {
  if (invoice.items.length === 1) return;
  const items = invoice.items.filter((_, i) => i !== idx);
  const { items: itemsWithAmount, totalAmount } = recalcTotal(items, invoice.billSundries);
  setInvoice({ ...invoice, items: itemsWithAmount, totalAmount });
};

  const handleAddSundry = () => {
    const billSundries = [...invoice.billSundries, { ...emptySundry }];
    const { items: itemsWithAmount, totalAmount } = recalcTotal(invoice.items, billSundries);
    setInvoice({ ...invoice, billSundries, items: itemsWithAmount, totalAmount });
  };

  const handleRemoveSundry = (idx) => {
    const billSundries = invoice.billSundries.filter((_, i) => i !== idx);
    const { items: itemsWithAmount, totalAmount } = recalcTotal(invoice.items, billSundries);
    setInvoice({ ...invoice, billSundries, items: itemsWithAmount, totalAmount });
  };

  // Validation
  const validate = () => {
    const errs = {};
    if (!invoice.date || new Date(invoice.date) < new Date().setHours(0, 0, 0, 0)) errs.date = 'Date required and cannot be backdated';
    if (!invoice.customerName) errs.customerName = 'Required';
    if (!invoice.billingAddress) errs.billingAddress = 'Required';
    if (!invoice.shippingAddress) errs.shippingAddress = 'Required';
    if (!invoice.GSTIN) errs.GSTIN = 'Required';
    if (!invoice.items.length) errs.items = 'At least one item required';
    invoice.items.forEach((item, idx) => {
      if (!item.itemName) errs[`itemName${idx}`] = 'Required';
      if (item.price <= 0) errs[`price${idx}`] = 'Price must be > 0';
      if (item.quantity <= 0) errs[`quantity${idx}`] = 'Quantity must be > 0';
    });
    return errs;
  };

  // CRUD actions
  const handleSave = async () => {
    const errs = validate();
    setErrors(errs);
    if (Object.keys(errs).length) return;
    if (mode === 'create') {
      await axios.post('/invoices', invoice);
    } else {
      await axios.put(`/invoices/${id}`, invoice);
    }
    navigate('/invoices');
  };
  const handleDelete = async () => {
    await axios.delete(`/invoices/${id}`);
    navigate('/invoices');
  };

  return (
    <Paper sx={{ p: 3 }}>
      <Typography variant="h6">{mode === 'create' ? 'Create Invoice' : 'Edit Invoice'}</Typography>
      <Grid container spacing={2} sx={{ mt: 1 }}>
        <Grid item xs={12} sm={6}>
          <TextField label="Date" type="date" fullWidth
            value={invoice.date} onChange={e => handleChange('date', e.target.value)}
            InputLabelProps={{ shrink: true }} error={!!errors.date} helperText={errors.date} />
        </Grid>
        <Grid item xs={12} sm={6}>
          <TextField label="Customer Name" fullWidth
            value={invoice.customerName} onChange={e => handleChange('customerName', e.target.value)}
            error={!!errors.customerName} helperText={errors.customerName} />
        </Grid>
        <Grid item xs={12} sm={6}>
          <TextField label="Billing Address" fullWidth
            value={invoice.billingAddress} onChange={e => handleChange('billingAddress', e.target.value)}
            error={!!errors.billingAddress} helperText={errors.billingAddress} />
        </Grid>
        <Grid item xs={12} sm={6}>
          <TextField label="Shipping Address" fullWidth
            value={invoice.shippingAddress} onChange={e => handleChange('shippingAddress', e.target.value)}
            error={!!errors.shippingAddress} helperText={errors.shippingAddress} />
        </Grid>
        <Grid item xs={12} sm={6}>
          <TextField label="GSTIN" fullWidth
            value={invoice.GSTIN} onChange={e => handleChange('GSTIN', e.target.value)}
            error={!!errors.GSTIN} helperText={errors.GSTIN} />
        </Grid>
      </Grid>
      <Divider sx={{ my: 2 }} />
      <Typography variant="subtitle1">Items</Typography>
      {invoice.items.map((item, idx) => {
        console.log(item);
        return (
          <React.Fragment key={idx}>
            <Grid container spacing={1} alignItems="center">
              <Grid item xs={3}>
                <TextField label="Item Name" fullWidth value={item.itemName}
                  onChange={e => handleItemChange(idx, 'itemName', e.target.value)}
                  error={!!errors[`itemName${idx}`]} helperText={errors[`itemName${idx}`]} />
              </Grid>
              <Grid item xs={2}>
              <TextField
                label="Quantity"
                type="number"
                fullWidth
                value={parseInt(item.quantity)}
                onChange={e => handleItemChange(idx, 'quantity', Number(e.target.value))}
                error={!!errors[`quantity${idx}`]}
                helperText={errors[`quantity${idx}`]}
                inputProps={{ min: 1 }}
              />
            </Grid>
            <Grid item xs={2}>
              <TextField
                label="Price"
                type="number"
                fullWidth
                value={parseFloat(item.price)}
                onChange={e => handleItemChange(idx, 'price', Number(e.target.value))}
                error={!!errors[`price${idx}`]}
                helperText={errors[`price${idx}`]}
                inputProps={{ min: 0.01, step: 0.01 }}
              />
            </Grid>
            <Grid item xs={2}>
              <TextField label="Amount" type="number" fullWidth
                value={typeof item.amount === 'object' && item.amount !== null && '$numberDecimal' in item.amount
                  ? item.amount.$numberDecimal
                  : item.amount}
                InputProps={{ readOnly: true }}
              />
            </Grid>
            <Grid item xs={1}>
              <IconButton onClick={() => setInvoice(inv => ({
                ...inv, items: inv.items.filter((_, i) => i !== idx)
              }))} disabled={invoice.items.length === 1}><DeleteIcon /></IconButton>
            </Grid>
          </Grid>
          {idx < invoice.items.length - 1 && (
            <Divider sx={{ my: 1 }} />
          )}
        </React.Fragment>
      )})
}
      <Button startIcon={<AddIcon />} onClick={() => setInvoice(inv => ({
        ...inv, items: [...inv.items, { ...emptyItem }]
      }))} sx={{ mt: 1 }}>Add Item</Button>
      <Divider sx={{ my: 2 }} />
      <Typography variant="subtitle1">Bill Sundries</Typography>
      {invoice.billSundries.map((s, idx) => (
        <Grid container spacing={1} key={idx} alignItems="center">
          <Grid item xs={5}>
            <TextField label="Bill Sundry Name" fullWidth value={s.billSundryName}
              onChange={e => handleSundryChange(idx, 'billSundryName', e.target.value)} />
          </Grid>
          <Grid item xs={5}>
            <TextField label="Amount" type="number" fullWidth value={s.amount}
              onChange={e => handleSundryChange(idx, 'amount', Number(e.target.value))} />
          </Grid>
          <Grid item xs={2}>
            <IconButton onClick={() => setInvoice(inv => ({
              ...inv, billSundries: inv.billSundries.filter((_, i) => i !== idx)
            }))}><DeleteIcon /></IconButton>
          </Grid>
        </Grid>
      ))}
      <Button startIcon={<AddIcon />} onClick={() => setInvoice(inv => ({
        ...inv, billSundries: [...inv.billSundries, { ...emptySundry }]
      }))} sx={{ mt: 1 }}>Add Bill Sundry</Button>
      <Divider sx={{ my: 2 }} />
      <Typography variant="h6">
        Total Amount: {typeof invoice.totalAmount === 'object' && invoice.totalAmount !== null && '$numberDecimal' in invoice.totalAmount
          ? invoice.totalAmount.$numberDecimal
          : invoice.totalAmount}
      </Typography>
      <Grid container spacing={2} sx={{ mt: 2 }}>
        <Grid item>
          <Button variant="contained" color="primary" onClick={handleSave}>Save</Button>
        </Grid>
        <Grid item>
          <Button variant="outlined" onClick={() => navigate('/invoices')}>Cancel</Button>
        </Grid>
        {mode === 'edit' && (
          <Grid item>
            <Button variant="contained" color="error" onClick={handleDelete}>Delete</Button>
          </Grid>
        )}
      </Grid>
    </Paper>
  );
}

export default InvoiceDetail;