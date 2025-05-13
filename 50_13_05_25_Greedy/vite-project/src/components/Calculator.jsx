import React, { useState } from 'react'

export default function Calculator() {
    const[ num1 , setnum1] = useState(0);
    const [num2 , setnum2] = useState(0);
    const[Result, setResult] = useState(0);
    const calculate = (operator) => {
        const a = parseFloat(num1);
        const b = parseFloat(num2);
        switch(operator){
            case '+':

                 setResult(a+b);
                //  setResult(num1+num2);
                 break;
            case '-':
                 setResult(a-b);
                 break;
            case '*':
                 setResult(a*b);
                 break;
            case '/':
                 if(b===0){
                     setResult("Cannot divide by 0");
                     break;
                 }
                 setResult(a/b);
                 break;
        }
    }
  return (
    <div>
        <input type="number" name="num1" id="num1" onChange={(e) => setnum1(e.target.value)} placeholder='Number 1'/>
        <input type="number" name="num2" id="num2" onChange={(e) => setnum2(e.target.value)} placeholder='Number 2' />
        <br />
        <button onClick={()=>calculate('+')}>+</button>
        <button onClick={()=>calculate('-')}>-</button>
        <button onClick={()=>calculate('*')}>*</button>
        <button onClick={()=>calculate('/')}>/</button>
        <p>Result : {Result}</p>
    </div>
  )
}
