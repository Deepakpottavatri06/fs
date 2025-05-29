import React, { useRef } from "react";
function useLogger(value) {
   React.useEffect(() => {
      const id = setInterval(() => {
         console.log("Value is:", value);
      }, 1000);
      return () => clearInterval(id);
   }, []);
}


export default function MyApp() {
   const [count, setCount] = React.useState(0);
   const count_ref = useRef(0);
   const log = () => {
      console.log("Count is:", count);
      // console.log(count_ref);
   };
   React.useEffect(() => {
      const id = setInterval(log, 1000);
      return () => clearInterval(id);
   }, []);
   

   return <button onClick={() => {
      setCount(count + 1)
      count_ref.current+=1
   }}>+</button>;
}


