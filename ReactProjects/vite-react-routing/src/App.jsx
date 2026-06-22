//import './App.css'
import React from 'react'
import {BrowserRouter as Router, Routes, Route, Link} from 'react-router-dom'
import Home from './components/Home/Home'
import About from './components/About/About'
import Contact from './components/Contact/Contact'
import Team from './components/Team/Team'
import NotFound from './components/NotFound/NotFound'
import CurrentLocation from './components/CurrentLocation/CurrentLocation'
import Dashboard from './components/Dashboard/Dashboard'

function App() {
  return (
    <Router>
      <div className="min-h-screen bg-gray-100">
        <nav className='bg-blue-600 p-4'>
          <ul className="flex justify-center space-x-6">
            <li><Link className='text-white font-medium transition duration-300 hover:text-yellow-300'
              to="/">Home</Link></li>
            <li><Link 
              className='text-white font-medium transition duration-300 hover:text-yellow-300'
            to="/about">About</Link></li>
            <li><Link 
            className='text-white font-medium transition duration-300 hover:text-yellow-300'
            to="/contact">Contact</Link></li>
          </ul>
        </nav>
        <CurrentLocation/>
        <div className='container mx-auto py-8'>
          <Routes>
            <Route path='/' element={<Home/>}/>
            <Route path='/about' element={<About/>}>
              <Route path='team' element={<Team/>}/>
            </Route>
            <Route path='/contact' element={<Contact/>}/>
            <Route path='/dashboard' element={<Dashboard/>}/>
            <Route path='*' element={<NotFound/>}/>
          </Routes>
        </div>
        
      </div>
    </Router>
  )
}

export default App
