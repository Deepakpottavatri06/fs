import React from 'react'
import { useState } from 'react'
export default function Counter() {
  const [count, setCount] = useState(0)
    const increment = () => {
        setCount((prev)=> prev + 1)
    }
    const decrement = () => {
        setCount((prev)=> prev - 1)
    }
  return (
    <div>
      <h1>Counter : {count}</h1>
      <button onClick={increment}>Increment</button>
      <button onClick={decrement}>Decrement</button>
    </div>
  )
}
