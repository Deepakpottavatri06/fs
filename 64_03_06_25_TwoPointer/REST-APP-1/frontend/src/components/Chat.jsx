// File: src/components/Chat.jsx
import { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export default function Chat() {
  const navigate = useNavigate();
    useEffect(
      ()=>{
        const token = localStorage.getItem("token");
        const role = localStorage.getItem("role");
        if((!token) || (role!=="user") ){
          navigate("/notFound");
        }
      }
    );
  const [query, setQuery] = useState('');
  const [response, setResponse] = useState('');
  const [loading, setLoading] = useState(false);

  const askQuestion = async () => {
    if (!query) return;
    setLoading(true);
    try {
      const res = await axios.post('/api/rag/ask', { question: query });
      setResponse(res.data.answer);
    } catch (err) {
      setResponse('Failed to get response');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="max-w-xl mx-auto mt-10 p-6 bg-white rounded-2xl shadow-lg">
      <h2 className="text-2xl font-semibold mb-4">Ask a Question</h2>
      <textarea value={query} onChange={(e) => setQuery(e.target.value)} rows={4}
        className="w-full px-4 py-2 border rounded-xl" placeholder="Ask something..."></textarea>
      <button onClick={askQuestion} disabled={loading} className="w-full mt-4 bg-purple-600 text-white py-2 rounded-xl hover:bg-purple-700">
        {loading ? 'Thinking...' : 'Submit'}
      </button>
      {response && <p className="mt-4 text-gray-800">Response: {response}</p>}
    </div>
  );
}
