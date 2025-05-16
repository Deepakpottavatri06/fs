import React from 'react'
// import Header from './components/Header'
// import { ThemeProvider } from './components/ThemeContext'
import Calculator from './components/Calculator'
import CalculatorProvider from './components/CalculatorProvider'
export default function App() {
  return (
    <>
      {/* <ThemeProvider>
        <Header />
      </ThemeProvider> */}
      <CalculatorProvider>
        <Calculator />
      </CalculatorProvider>
      
    </>
  )
}
