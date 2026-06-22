import { useState } from 'react'
import './App.css'
import Counter from './components/Counter/Counter'
import Display from './components/Display/Display'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <div>
        <h1>Welcome</h1>
        <Counter/>
        <Display/>
      </div>
    </>
  )
}

export default App
