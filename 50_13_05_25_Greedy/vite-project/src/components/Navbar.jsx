import React from 'react'
import {Link} from 'react-router-dom'
export default function Navbar() {
  return (
    <div>
        <ul>
            <li><Link to="/">Greeting</Link></li>
            <li><Link to="/counter">Counter</Link></li>
            <li><Link to="/showandhide">showandhide</Link></li>
            <li><Link to="/cal">Calculator</Link></li>
            <li><Link to="/form">Form</Link></li>
        </ul>
    </div>
  )
}
