// File: src/components/Navbar.jsx
import { useNavigate } from 'react-router-dom';

export default function Navbar() {
    const navigate = useNavigate();
    const token = localStorage.getItem("token");
    const user = localStorage.getItem("user");
    const onLogout = () =>{
      localStorage.removeItem("token");
      localStorage.removeItem("role");
      localStorage.removeItem("user");
      navigate("/login")
    }
  return (
    <nav className="bg-blue-600 text-white py-4 px-6 flex justify-between items-center shadow-md">
      <h1
        className="text-xl font-bold cursor-pointer"
        onClick={() => navigate("/")}
      >
         RAG PDF App
      </h1>

      <div className="space-x-4">
        {user ? (
          <>
            <span className="text-sm">Hello, {user}</span>
            <button
              onClick={onLogout}
              className="bg-white text-blue-600 px-4 py-1 rounded-xl font-medium hover:bg-gray-200 transition"
            >
              Logout
            </button>
          </>
        ) : (
          <>
            <button
              onClick={() => navigate("/login")}
              className="bg-white text-blue-600 px-4 py-1 rounded-xl font-medium hover:bg-gray-200 transition"
            >
              Login
            </button>
            <button
              onClick={() => navigate("/register")}
              className="bg-blue-500 border border-white px-4 py-1 rounded-xl font-medium hover:bg-blue-400 transition"
            >
              Register
            </button>
          </>
        )}
      </div>
    </nav>
  );
}
