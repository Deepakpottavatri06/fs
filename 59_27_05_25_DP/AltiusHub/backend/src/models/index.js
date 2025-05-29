import mongoose from 'mongoose';

const invoiceHeaderSchema = new mongoose.Schema({
    date: { type: String, required: true },
    invoiceNumber: { type: Number, unique: true },
    customerName: { type: String, required: true },
    billingAddress: { type: String, required: true },
    shippingAddress: { type: String, required: true },
    GSTIN: { type: String, required: true },
    totalAmount: { type: mongoose.Types.Decimal128, required: true }
});

const invoiceItemSchema = new mongoose.Schema({
    invoiceId: { type: String, ref: 'InvoiceHeader', required: true }, // Use String for UUID
    itemName: { type: String, required: true },
    quantity: { type: mongoose.Types.Decimal128, required: true },
    price: { type: mongoose.Types.Decimal128, required: true },
    amount: { type: mongoose.Types.Decimal128, required: true }
});

const invoiceBillSundrySchema = new mongoose.Schema({
    invoiceId: { type: String, ref: 'InvoiceHeader', required: true }, // Use String for UUID
    billSundryName: { type: String, required: true },
    amount: { type: mongoose.Types.Decimal128, required: true }
});

const InvoiceHeader = mongoose.model('InvoiceHeader', invoiceHeaderSchema);
const InvoiceItem = mongoose.model('InvoiceItem', invoiceItemSchema);
const InvoiceBillSundry = mongoose.model('InvoiceBillSundry', invoiceBillSundrySchema);

export { InvoiceHeader, InvoiceItem, InvoiceBillSundry };