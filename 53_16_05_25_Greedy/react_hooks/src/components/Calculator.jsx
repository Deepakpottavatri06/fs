import React from 'react';
import { CalculatorContext } from './CalculatorContext';
import { useContext } from 'react';
const Calculator = () => {
    const { state, dispatch } = useContext(CalculatorContext);
  const handleNumberClick = (number) => {
    dispatch({ type: 'SET_NUMBER', payload: number });
  };

  const handleOperationClick = (operation) => {
    dispatch({ type: 'SET_OPERATION', payload: operation });
  };

  const handleCalculate = () => {
    dispatch({ type: 'CALCULATE' });
  };

  const handleClear = () => {
    dispatch({ type: 'CLEAR' });
  };

  return (
    <div className="calculator" style={{width: '200px', margin: '20px auto'}}>
      <input
        type="text"
        readOnly
        value={state.currentValue}
        style={{ width: '100%', height: '40px', textAlign: 'right', marginBottom: '10px' }}
      />
      <div>
        <button onClick={() => handleNumberClick('7')}>7</button>
        <button onClick={() => handleNumberClick('8')}>8</button>
        <button onClick={() => handleNumberClick('9')}>9</button>
        <button onClick={() => handleOperationClick('/')}>/</button>
      </div>
      <div>
        <button onClick={() => handleNumberClick('4')}>4</button>
        <button onClick={() => handleNumberClick('5')}>5</button>
        <button onClick={() => handleNumberClick('6')}>6</button>
        <button onClick={() => handleOperationClick('*')}>*</button>
      </div>
      <div>
        <button onClick={() => handleNumberClick('1')}>1</button>
        <button onClick={() => handleNumberClick('2')}>2</button>
        <button onClick={() => handleNumberClick('3')}>3</button>
        <button onClick={() => handleOperationClick('-')}>-</button>
      </div>
      <div>
        <button onClick={() => handleNumberClick('0')}>0</button>
        <button onClick={handleClear}>C</button>
        <button onClick={handleCalculate}>=</button>
        <button onClick={() => handleOperationClick('+')}>+</button>
      </div>
    </div>
  );
};

export default Calculator;
