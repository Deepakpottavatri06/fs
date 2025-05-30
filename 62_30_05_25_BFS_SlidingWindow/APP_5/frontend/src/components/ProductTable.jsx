// import React, { useEffect, useState } from 'react';
// import {
//   Table, TableBody, TableCell, TableContainer, TableHead, TableRow,
//   Paper, TablePagination, CircularProgress, TextField, Button, Typography
// } from '@mui/material';
// import axios from 'axios';

// const ProductTable = () => {
//   const [products, setProducts] = useState([]);
//   const [loading, setLoading] = useState(true);
//   const [searchQuery, setSearchQuery] = useState('');
//   const [page, setPage] = useState(0);
//   const [rowsPerPage, setRowsPerChange] = useState(10);
//   const [editRowId, setEditRowId] = useState(null);
//   const [editData, setEditData] = useState({});

//   useEffect(() => {
//     const fetchProducts = async () => {
//       try {
//         const token = localStorage.getItem('auth') ? JSON.parse(localStorage.getItem('auth')).token : '';
//         if (!token) {
//           // Handle case where token is not available
//           console.error('No token found');
//           return;
//         }
//         const res = await axios.get('/api/products',
//           {
//             headers: {
//               Authorization: `Bearer ${token}`
//             }
//           }
//         );
//         setProducts(res.data);
//       } catch (err) {
//         console.error('Error fetching products:', err);
//       } finally {
//         setLoading(false);
//       }
//     };
//     fetchProducts();
//   }, []);

//   const handleChangePage = (_, newPage) => setPage(newPage);

//   const changeRows = (e) => {
//     setRowsPerChange(parseInt(e.target.value));
//     setPage(0);
//   };

//   const filteredProducts = products.filter((p) =>
//     p.name.toLowerCase().includes(searchQuery.toLowerCase())
//   );

//   const handleEditClick = (product) => {
//     setEditRowId(product._id);
//     setEditData(product);
//   };

//   const handleEditChange = (e, field) => {
//     setEditData({ ...editData, [field]: e.target.value });
//   };

//   const handleSaveClick = async (id) => {
//     try {
//       const res = await axios.put(`/api/products/${id}`, editData);
//       setProducts((prev) =>
//         prev.map((p) => (p._id === id ? res.data : p))
//       );
//       setEditRowId(null);
//     } catch (err) {
//       console.error('Failed to update product:', err);
//     }
//   };

//   const handleDelete = async (id) => {
//     try {
//       await axios.delete(`/api/products/${id}`);
//       setProducts((prev) => prev.filter((p) => p._id !== id));
//     } catch (err) {
//       console.error('Failed to delete product:', err);
//     }
//   };

//   return (
//     <Paper>
//       {loading ? (
//         <div style={{ padding: 20, textAlign: 'center' }}>
//           <CircularProgress />
//         </div>
//       ) : (
//         <>
//           <Typography variant="h4" component="h2" sx={{ padding: 2 }}>
//             Products List
//           </Typography>

//           <div style={{ padding: 16 }}>
//             <TextField
//               label="Search Products by Name"
//               variant="outlined"
//               fullWidth
//               value={searchQuery}
//               onChange={(e) => setSearchQuery(e.target.value)}
//             />
//           </div>

//           <TableContainer>
//             <Table>
//               <TableHead>
//                 <TableRow>
//                   <TableCell><strong>Name</strong></TableCell>
//                   <TableCell><strong>Price</strong></TableCell>
//                   <TableCell><strong>Category</strong></TableCell>
//                   <TableCell><strong>In Stock</strong></TableCell>
//                   <TableCell><strong>Actions</strong></TableCell>
//                 </TableRow>
//               </TableHead>
//               <TableBody>
//                 {filteredProducts
//                   .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
//                   .map((product) => (
//                     <TableRow key={product._id}>
//                       <TableCell>
//                         {editRowId === product._id ? (
//                           <TextField
//                             value={editData.name}
//                             onChange={(e) => handleEditChange(e, 'name')}
//                           />
//                         ) : (
//                           product.name
//                         )}
//                       </TableCell>
//                       <TableCell>
//                         {editRowId === product._id ? (
//                           <TextField
//                             type="number"
//                             value={editData.price}
//                             onChange={(e) => handleEditChange(e, 'price')}
//                           />
//                         ) : (
//                           '$'+product.price.toFixed(2)
//                         )}
//                       </TableCell>
//                       <TableCell>
//                         {editRowId === product._id ? (
//                           <TextField
//                             value={editData.category}
//                             onChange={(e) => handleEditChange(e, 'category')}
//                           />
//                         ) : (
//                           product.category
//                         )}
//                       </TableCell>
//                       <TableCell>
//                         {editRowId === product._id ? (
//                           <TextField
//                             value={editData.inStock}
//                             onChange={(e) => handleEditChange(e, 'inStock')}
//                           />
//                         ) : (
//                           product.inStock ? 'Yes' : 'No'
//                         )}
//                       </TableCell>
//                       <TableCell>
//                         {editRowId === product._id ? (
//                           <Button
//                             variant="contained"
//                             color="success"
//                             onClick={() => handleSaveClick(product._id)}
//                             sx={{ mr: 1 }}
//                           >
//                             Save
//                           </Button>
//                         ) : (
//                           <>
//                             <Button
//                               color="primary"
//                               onClick={() => handleEditClick(product)}
//                               sx={{ mr: 1 }}
//                             >
//                               Edit
//                             </Button>
//                             <Button
//                               variant="contained"
//                               color="error"
//                               onClick={() => handleDelete(product._id)}
//                             >
//                               Delete
//                             </Button>
//                           </>
//                         )}

//                       </TableCell>
//                     </TableRow>
//                   ))}
//               </TableBody>
//             </Table>
//           </TableContainer>

//           <TablePagination
//             component="div"
//             count={filteredProducts.length}
//             page={page}
//             onPageChange={handleChangePage}
//             onRowsPerPageChange={changeRows}
//             rowsPerPage={rowsPerPage}
//             rowsPerPageOptions={[10, 25, 50]}
//           />
//         </>
//       )}
//     </Paper>
//   );
// };

// export default ProductTable;

import React, { useEffect, useState } from 'react';
import {
  Table, TableBody, TableCell, TableContainer, TableHead, TableRow,
  Paper, TablePagination, CircularProgress, TextField, Button, Typography
} from '@mui/material';
import axios from 'axios';

const ProductTable = () => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [searchQuery, setSearchQuery] = useState('');
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(10);
  const [editRowId, setEditRowId] = useState(null);
  const [editData, setEditData] = useState({});
  const role = JSON.parse(localStorage.getItem('auth'))?.role;

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const res = await axios.get('/api/products', {
          headers: {
            Authorization: `Bearer ${JSON.parse(localStorage.getItem('auth'))?.token}`
          }
        });
        setProducts(res.data);
      } catch (err) {
        console.error('Error fetching products:', err);
      } finally {
        setLoading(false);
      }
    };
    fetchProducts();
  }, []);

  const handleChangePage = (_, newPage) => setPage(newPage);
  const handleChangeRows = (e) => {
    setRowsPerPage(parseInt(e.target.value));
    setPage(0);
  };

  const filteredProducts = products.filter((p) =>
    p.name.toLowerCase().includes(searchQuery.toLowerCase())
  );

  const handleEditClick = (product) => {
    setEditRowId(product._id);
    setEditData(product);
  };

  const handleEditChange = (e, field) => {
    setEditData({ ...editData, [field]: e.target.value });
  };

  const handleSaveClick = async (id) => {
    try {
      const res = await axios.put(`/api/products/${id}`, editData, {
        headers: {
          Authorization: `Bearer ${JSON.parse(localStorage.getItem('auth'))?.token}`
        }
      });
      setProducts((prev) =>
        prev.map((p) => (p._id === id ? res.data : p))
      );
      setEditRowId(null);
    } catch (err) {
      console.error('Failed to update product:', err);
    }
  };

  const handleDelete = async (id) => {
    try {
      await axios.delete(`/api/products/${id}`, {
        headers: {
          Authorization: `Bearer ${JSON.parse(localStorage.getItem('auth'))?.token}`
        }
      });
      setProducts((prev) => prev.filter((p) => p._id !== id));
    } catch (err) {
      console.error('Failed to delete product:', err);
    }
  };

  return (
    <Paper>
      <Typography variant="h5" color="primary" sx={{ p: 2 }}>
        Products List
      </Typography>

      {loading ? (
        <div style={{ padding: 20, textAlign: 'center' }}>
          <CircularProgress />
        </div>
      ) : (
        <>
          <div style={{ padding: 16 }}>
            <TextField
              label="Search by name"
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
                  {role === 'admin' && <TableCell><strong>Actions</strong></TableCell>}
                </TableRow>
              </TableHead>
              <TableBody>
                {filteredProducts
                  .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                  .map((product) => (
                    <TableRow key={product._id}>
                      <TableCell>
                        {editRowId === product._id ? (
                          <TextField
                            value={editData.name}
                            onChange={(e) => handleEditChange(e, 'name')}
                          />
                        ) : (
                          product.name
                        )}
                      </TableCell>
                      <TableCell>
                        {editRowId === product._id ? (
                          <TextField
                            type="number"
                            value={editData.price}
                            onChange={(e) => handleEditChange(e, 'price')}
                          />
                        ) : (
                          product.price.toFixed(2)
                        )}
                      </TableCell>
                      <TableCell>
                        {editRowId === product._id ? (
                          <TextField
                            value={editData.category}
                            onChange={(e) => handleEditChange(e, 'category')}
                          />
                        ) : (
                          product.category
                        )}
                      </TableCell>
                      <TableCell>
                        {editRowId === product._id ? (
                          <TextField
                            value={editData.inStock}
                            onChange={(e) => handleEditChange(e, 'inStock')}
                          />
                        ) : (
                          product.inStock ? 'Yes' : 'No'
                        )}
                      </TableCell>
                      {role === 'admin' && (
                        <TableCell>
                          {editRowId === product._id ? (
                            <Button
                              variant="contained"
                              color="success"
                              onClick={() => handleSaveClick(product._id)}
                            >
                              Save
                            </Button>
                          ) : (
                            <>
                              <Button
                                variant="outlined"
                                color="primary"
                                onClick={() => handleEditClick(product)}
                                style={{ marginRight: 8 }}
                              >
                                Edit
                              </Button>
                              <Button
                                variant="outlined"
                                color="error"
                                onClick={() => handleDelete(product._id)}
                              >
                                Delete
                              </Button>
                            </>
                          )}
                        </TableCell>
                      )}
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
            onRowsPerPageChange={handleChangeRows}
            rowsPerPage={rowsPerPage}
            rowsPerPageOptions={[10, 25, 50]}
          />
        </>
      )}
    </Paper>
  );
};

export default ProductTable;
