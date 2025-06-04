import { useState, useEffect} from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

export default function FileUploader() {
  const navigate = useNavigate();
  useEffect(
    ()=>{
      const token = localStorage.getItem("token");
      const role = localStorage.getItem("role");
      if(!token || role!=="admin" ){
        navigate("/notFound");
      }
    }
  )
  const [file, setFile] = useState(null);
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState('');

  const upload = async () => {
    if (!file) {
      setMessage("Please select a PDF file first.");
      return;
    }

    setLoading(true);
    setMessage('');

    const formData = new FormData();
    formData.append("pdf", file);

    try {
      await axios.post("http://localhost:5000/api/pdf/upload", formData, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
          'Content-Type': 'multipart/form-data'
        }
      });
      setMessage("✅ Uploaded successfully");
      setFile(null);
    } catch (err) {
      console.error(err);
      setMessage("❌ Upload failed. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100 px-4">
      <div className="w-full max-w-md p-8 bg-white rounded-2xl shadow-lg">
        <h2 className="text-2xl font-semibold text-center text-blue-600 mb-6">Upload PDF (Admin Only)</h2>

        <div className="space-y-4">
          <input
            type="file"
            accept="application/pdf"
            onChange={(e) => setFile(e.target.files[0])}
            className="w-full px-4 py-2 border rounded-xl bg-gray-50 focus:outline-none focus:ring-2 focus:ring-blue-500"
          />

          <button
            onClick={upload}
            disabled={loading}
            className={`w-full bg-blue-600 text-white py-3 rounded-xl hover:bg-blue-700 transition duration-200 ${
              loading ? 'opacity-50 cursor-not-allowed' : ''
            }`}
          >
            {loading ? (
              <span className="flex justify-center items-center">
                <svg
                  className="animate-spin h-5 w-5 mr-2 text-white"
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                >
                  <circle
                    className="opacity-25"
                    cx="12"
                    cy="12"
                    r="10"
                    stroke="currentColor"
                    strokeWidth="4"
                  ></circle>
                  <path
                    className="opacity-75"
                    fill="currentColor"
                    d="M4 12a8 8 0 018-8v4a4 4 0 00-4 4H4z"
                  ></path>
                </svg>
                Uploading...
              </span>
            ) : (
              'Upload PDF'
            )}
          </button>

          {message && (
            <p className={`text-center text-sm ${message.includes("success") ? "text-green-600" : "text-red-500"}`}>
              {message}
            </p>
          )}
        </div>
      </div>
    </div>
  );
}
