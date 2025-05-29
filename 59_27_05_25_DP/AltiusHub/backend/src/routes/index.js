import express from 'express';
import { InvoiceHeader, InvoiceItem, InvoiceBillSundry } from '../models/index.js';

const router = express.Router();

// GET all invoices
router.get('/invoices', async (req, res) => {
    try {
        const invoices = await InvoiceHeader.find();
        res.status(200).json(invoices);
    } catch (error) {
        res.status(500).json({ message: 'Error retrieving invoices', error });
    }
});

// GET invoice by ID
router.get('/invoices/:id', async (req, res) => {
    try {
        const invoice = await InvoiceHeader.findById(req.params.id);
        if (!invoice) return res.status(404).json({ message: 'Invoice not found' });
        const items = await InvoiceItem.find({ invoiceId: invoice._id });
        const billSundries = await InvoiceBillSundry.find({ invoiceId: invoice._id });
        res.status(200).json({ ...invoice.toObject(), items, billSundries });
    } catch (error) {
        res.status(500).json({ message: 'Error retrieving invoice', error });
    }
});

// CREATE invoice
router.post('/invoices', async (req, res) => {
    const { date, customerName, billingAddress, shippingAddress, GSTIN, items, billSundries, invoiceNumber } = req.body;
    // Calculate totalAmount
    const itemsTotal = items.reduce((sum, item) => sum + (item.quantity * item.price), 0);
    const billSundriesTotal = billSundries.reduce((sum, s) => sum + s.amount, 0);
    const totalAmount = itemsTotal + billSundriesTotal;
    if (totalAmount <= 0) {
        return res.status(400).json({ message: 'Total amount must be greater than 0' });
    }
    try {
        // Auto-generate invoiceNumber if not provided
        let newInvoiceNumber = invoiceNumber;
        if (!newInvoiceNumber) {
            const lastInvoice = await InvoiceHeader.findOne().sort({ invoiceNumber: -1 });
            newInvoiceNumber = lastInvoice && lastInvoice.invoiceNumber ? lastInvoice.invoiceNumber + 1 : 1;
        }
        const invoice = new InvoiceHeader({ date, customerName, billingAddress, shippingAddress, GSTIN, totalAmount, invoiceNumber: newInvoiceNumber });
        await invoice.save();
        for (const item of items) {
            const invoiceItem = new InvoiceItem({ invoiceId: invoice._id, ...item });
            await invoiceItem.save();
        }
        for (const s of billSundries) {
            const invoiceBillSundry = new InvoiceBillSundry({ invoiceId: invoice._id, ...s });
            await invoiceBillSundry.save();
        }
        res.status(201).json(invoice);
    } catch (error) {
        res.status(500).json({ message: 'Error creating invoice', error });
    }
});

// UPDATE invoice
router.put('/invoices/:id', async (req, res) => {
    try {
        const updatedInvoice = await InvoiceHeader.findByIdAndUpdate(req.params.id, req.body, { new: true });
        if (!updatedInvoice) return res.status(404).json({ message: 'Invoice not found' });
        res.status(200).json(updatedInvoice);
    } catch (error) {
        res.status(500).json({ message: 'Error updating invoice', error });
    }
});

// DELETE invoice
router.delete('/invoices/:id', async (req, res) => {
    try {
        const deletedInvoice = await InvoiceHeader.findByIdAndDelete(req.params.id);
        if (!deletedInvoice) return res.status(404).json({ message: 'Invoice not found' });
        res.status(204).send();
    } catch (error) {
        res.status(500).json({ message: 'Error deleting invoice', error });
    }
});

// Export the router
export default router;