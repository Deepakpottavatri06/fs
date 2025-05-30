
const mongoose = require('mongoose');
const Product = require('./models/Products'); // Adjust the path as necessary

const categories = ['Electronics', 'Clothing', 'Books', 'Home', 'Sports'];

const generateProducts = () => {
  const products = [];

  for (let i = 1; i <= 100; i++) {
    products.push({
      name: `Product ${i}`,
      price: parseFloat((Math.random() * 100).toFixed(2)),
      category: categories[Math.floor(Math.random() * categories.length)],
      inStock: Math.random() < 0.7, // ~70% chance in stock
    });
  }

  return products;
};

const seedProducts = async () => {
  try {
    await mongoose.connect("mongodb://localhost:27017/app1");
    await Product.deleteMany({});
    const products = generateProducts();
    await Product.insertMany(products);
    console.log('Sample products inserted');
    process.exit();
  } catch (error) {
    console.error('Seeding error:', error);
    process.exit(1);
  }
};

seedProducts();
