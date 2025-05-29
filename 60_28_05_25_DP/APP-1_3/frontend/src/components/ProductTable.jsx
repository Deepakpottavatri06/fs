import React, { useEffect, useState } from 'react';
import {
Table, TableBody, TableCell, TableContainer, TableHead, TableRow,
 Paper, TablePagination, CircularProgress, TextField
} from '@mui/material';
import axios from 'axios';

const ProductTable = () => {
 const [products, setProducts] = useState([]);
 const [loading, setLoading] = useState(true);
 const [searchQuery, setSearchQuery] = useState('');

 const [page, setPage] = useState(0);
 const [rowsPerPage, setRowsPerChange] = useState(10);

 useEffect(() => {
  const fetchProducts = async () => {
   try {
    const res = await axios.get('/api/products');
    setProducts(res.data);
   } catch (err) {
    console.error('Error fetching products:', err);
   } finally {
    setLoading(false);
   }
  };

  fetchProducts();
 }, []);

 const handleChangePage = (_, newPage) => {
  setPage(newPage);
 };

 const changeRows = (event) => {
  setRowsPerChange(parseInt(event.target.value));
  setPage(0);
 };

 // Filtered products based on search query
 const filteredProducts = products.filter((product) =>
  product.name.toLowerCase().includes(searchQuery.toLowerCase())
 );

 return (
  <Paper>
   {loading ? (
    <div style={{ padding: 20, textAlign: 'center' }}>
     <CircularProgress />
    </div>
   ) : (
    <>
     {/* 🔍 Search bar */}
     <div style={{ padding: 16 }}>
      <TextField
       label="Search Products by Name"
       variant="outlined"
       fullWidth
       value={searchQuery}
       onChange={(e) => setSearchQuery(e.target.value)}
      />
     </div>

     <TableContainer>
      <Table>
       <TableHead>
        <TableRow>
         <TableCell><strong>Name</strong></TableCell>
         <TableCell><strong>Price</strong></TableCell>
         <TableCell><strong>Category</strong></TableCell>
         <TableCell><strong>In Stock</strong></TableCell>
        </TableRow>
       </TableHead>
       <TableBody>
        {filteredProducts
         .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
         .map((product) => (
          <TableRow key={product._id}>
           <TableCell>{product.name}</TableCell>
           <TableCell>{product.price.toFixed(2)}</TableCell>
           <TableCell>{product.category}</TableCell>
           <TableCell>{product.inStock ? 'Yes' : 'No'}</TableCell>
          </TableRow>
         ))}
       </TableBody>
      </Table>
     </TableContainer>

     <TablePagination
      component="div"
      count={filteredProducts.length}
      page={page}
      onPageChange={handleChangePage}
      onRowsPerPageChange={changeRows}
      rowsPerPage={rowsPerPage}
      rowsPerPageOptions={[10, 25, 50, 100]}
     />
    </>
   )}
  </Paper>
 );
};

export default ProductTable;
