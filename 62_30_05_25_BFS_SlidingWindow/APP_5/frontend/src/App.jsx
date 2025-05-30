// import React, { useState, useEffect } from 'react';
// import { Container } from '@mui/material';
// import LoginForm from './components/LoginForm';
// import RegisterForm from './components/RegisterForm';
// import ProductsTable from './components/ProductTable';
// import Navbar from './components/Navbar';

// function App() {
//   const [isAuthenticated, setIsAuthenticated] = useState(false);
//   const [showRegister, setShowRegister] = useState(false);

//   useEffect(() => {
//     const token = JSON.parse(localStorage.getItem('auth'))?.token;
//     if (token) setIsAuthenticated(true);
//   }, []);

//   const handleLogin = () => {
//     setIsAuthenticated(true);
//   };

//   const handleLogout = () => {
//     localStorage.removeItem('auth');
//     setIsAuthenticated(false);
//     setShowRegister(false);
//   };

//   return (
//     <>
//       <Navbar
//         isAuthenticated={isAuthenticated}
//         onRegisterClick={() => setShowRegister(true)}
//         onLoginClick={() => setShowRegister(false)}
//         onLogoutClick={handleLogout}
//       />
//       <Container sx={{ mt: 4 }}>
//         {!isAuthenticated ? (
//           showRegister ? (
//             <RegisterForm onSwitchToLogin={() => setShowRegister(false)} />
//           ) : (
//             <LoginForm onLoginSuccess={handleLogin} />
//           )
//         ) : (
//           <ProductsTable />
//         )}
//       </Container>
//     </>
//   );
// }

// export default App;

import React, { useState, useEffect } from 'react';
import { Container } from '@mui/material';
import LoginForm from './components/LoginForm';
import RegisterForm from './components/RegisterForm';
import ProductsTable from './components/ProductTable';
import Navbar from './components/Navbar';

function App() {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [role, setRole] = useState(null);
  const [showRegister, setShowRegister] = useState(false);

  useEffect(() => {
    const auth = JSON.parse(localStorage.getItem('auth'));
    if (auth?.token && auth?.role) {
      setIsAuthenticated(true);
      setRole(auth.role);
    }
  }, []);

  const handleLogin = () => {
    const auth = JSON.parse(localStorage.getItem('auth'));
    if (auth?.token && auth?.role) {
      setIsAuthenticated(true);
      setRole(auth.role);
    }
  };

  const handleLogout = () => {
    localStorage.removeItem('auth');
    setIsAuthenticated(false);
    setRole(null);
    setShowRegister(false);
  };

  return (
    <>
      <Navbar
        isAuthenticated={isAuthenticated}
        onRegisterClick={() => setShowRegister(true)}
        onLoginClick={() => setShowRegister(false)}
        onLogoutClick={handleLogout}
      />
      <Container sx={{ mt: 4 }}>
        {!isAuthenticated ? (
          showRegister ? (
            <RegisterForm onSwitchToLogin={() => setShowRegister(false)} />
          ) : (
            <LoginForm onLoginSuccess={handleLogin} />
          )
        ) : (
          <ProductsTable role={role} />
        )}
      </Container>
    </>
  );
}

export default App;
