import React from 'react';
import ProductTable from './components/ProductTable';

function App() {
  return (
    <div style={{ padding: 20 }}>
      <h2>Product Inventory</h2>
      <ProductTable />
    </div>
  );
}

export default App;

// import { Routes, Route } from 'react-router-dom';
// import Navbar from './components/Navbar';
// import NewProfile from './pages/NewProfile';
// import AllProfiles from './pages/AllProfiles';
// import ProfileDetail from './components/ProfileDetail';

// function App() {
//   return (
//     <>
//       <Navbar />
//       <Routes>
//         <Route path="/" element={<NewProfile />} />
//         <Route path="/profile" element={<NewProfile />} />
//         <Route path="/profiles" element={<AllProfiles />} />
//         <Route path="/profile/:id" element={<ProfileDetail />} />
//       </Routes>
//     </>
//   );
// }

// export default App;
