import { useState } from 'react'
import './App.css'

function App() {
  const [count, setCount] = useState(0);
  const [step, setStep] = useState(1);

  const incrementTwice = () => {
    setCount(prevCount => prevCount + 1);
    setCount(prevCount => prevCount + 1);
  }

  function increaseCounter(){
    setCount(count + step)
  }

  function decreaseCounter(){
    setCount(count - step)
  }

  return (
    <div className="app-container">
      <h1>Counter: {count}</h1>
      <input 
        type='number' 
        value={step} 
        onChange={(e)=> setStep(parseInt(e.target.value))}></input>
      <button onClick={increaseCounter}>Increment</button>
      <button onClick={decreaseCounter}>Decrement</button>
      <button onClick={incrementTwice}>+2</button>
      
    </div>
  )
}

export default App
