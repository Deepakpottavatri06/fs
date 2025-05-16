import { CalculatorContext } from "./CalculatorContext";
import { useReducer } from "react";
const calculatorReducer = (state, action) => {
  switch (action.type) {
    case 'SET_NUMBER':
      return { 
        ...state, 
        currentValue: state.currentValue === '0' 
                      ? action.payload 
                      : state.currentValue + action.payload 
      };

    case 'SET_OPERATION':
      return {
        ...state,
        operation: action.payload,
        previousValue: state.currentValue,
        currentValue: '0'
      };

    case 'CALCULATE':
      const prev = parseFloat(state.previousValue);
      const current = parseFloat(state.currentValue);
      let result = 0;
      switch (state.operation) {
        case '+':
          result = prev + current;
          break;
        case '-':
          result = prev - current;
          break;
        case '*':
          result = prev * current;
          break;
        case '/':
          result = current === 0 ? 'Error' : prev / current;
          break;
        default:
          result = current;
      }
      return {
        operation: null,
        previousValue: null,
        currentValue: String(result),
      };

    case 'CLEAR':
      return { operation: null, previousValue: null, currentValue: '0' };

    default:
      return state;
  }
};

export default function CalculatorProvider({children}){
    
    
  const [state, dispatch] = useReducer(calculatorReducer, {
    currentValue: '0',
    previousValue: null,
    operation: null
  });

  return (
    <CalculatorContext.Provider value={{state, dispatch}}>
        {children}
    </CalculatorContext.Provider>
  )
}