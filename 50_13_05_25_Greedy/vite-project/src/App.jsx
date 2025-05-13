import React from 'react'
import Greeting from './components/Greeting'
import Counter from './components/Counter'
import Users from './components/Users'
import ShowAndHide from './components/ShowAndHide'
import Navbar from './components/Navbar'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import Calculator from './components/Calculator'
import SimpleForm from './components/SimpleForm'

export default function App() {
  return (
    <div>
      <Router>
        <Navbar />
        <Routes>
          <Route path="/" element={<Greeting />} />
          <Route path="/counter" element={<Counter />} />
          <Route path="/showandhide" element={<ShowAndHide />} />
          <Route path="/cal" element={<Calculator/>}/>
          <Route path='/form' element={<SimpleForm/>}/>
        </Routes>
      </Router>

     
    </div>
  )
}
