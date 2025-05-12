import React from 'react'

export default function Greeting(props) {
  return (
    <div>
        <h1>Hello {props.name}, Welcome to React!</h1>
        {props.children}
    </div>
  )
}
