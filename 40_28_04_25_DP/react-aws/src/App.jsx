import React from 'react'
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom'
import BillingInventoryForm from './components/BillingInventory'
import './App.css';
export default function App() {
  return (
    <>
    <Router>
      <Routes>
        <Route path="/" element={<BillingInventoryForm />} />
      </Routes>
    </Router>
    </>
  )
}
