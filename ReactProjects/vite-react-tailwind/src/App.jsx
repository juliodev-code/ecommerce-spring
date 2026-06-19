import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import heroImg from './assets/hero.png'
import './App.css'

function App() {
  const [count, setCount] = useState(0)

  return (
    <div className='bg-gray-100 flex justify-center items-center h-screen'>
      <div className='bg-white p-8 rounded-lg shadow-lg'>
        <h1 className='text-2xl font-bold text-gray-800'>Hello Tailwind</h1>
        <p className='text-gray-600'>This a simple example using tailwind css</p>
      </div>
    </div>
  )
}

export default App
