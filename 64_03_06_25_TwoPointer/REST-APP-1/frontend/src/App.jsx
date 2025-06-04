// App.jsx
import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate, useNavigate } from "react-router-dom";
import Login from "./components/Login";
import FileUploader from "./components/FileUploader";
import Chat from "./components/Chat";
import Navbar from "./components/Navbar";
import Register from "./components/Register";

function App() {

  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/" element={<Navigate to="/login" />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register/>}/>
        <Route path="/upload" element={<FileUploader />} />
        <Route path="/chat" element={<Chat />} />
        {/* fallback */}
        <Route path="*" element={<div className="text-center mt-20 text-red-600 text-xl">404 Not Found</div>} />
      </Routes>
    </Router>
  );
}

export default App;
