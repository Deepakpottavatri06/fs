import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
// import './index.css'
import {BrowserRouter} from 'react-router-dom'
import App from './App.jsx'
import axios from 'axios'

axios.defaults.baseURL = "http://10.11.18.51:5000"
createRoot(document.getElementById('root')).render(
  <StrictMode>
    <BrowserRouter>
    <App />
  </BrowserRouter>,
  </StrictMode>
)
