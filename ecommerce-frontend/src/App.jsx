import React from 'react'
import { FaBeer } from 'react-icons/fa'
import './App.css'
import { BrowserRouter as Router, Routes, Route} from 'react-router-dom'
import { Toaster } from 'react-hot-toast'
import Products from './components/products/Products'
import Home from './components/home/Home'
import Navbar from './components/shared/Navbar'
import About from './components/about/About'
import Contact from './components/contact/Contact'
import Cart from './components/cart/Cart'
import LoginPage from './components/auth/LoginPage'
import PrivateRoute from './components/PrivateRoute'
import Register from './components/auth/Register'



function App() {

  return (
    <React.Fragment>
      <Router>
        <Navbar/>
        <Routes>
          <Route path="/" element={<Home/>}/>
          <Route path="/products" element={<Products/>}/>
          <Route path="/about" element={<About/>}/>
          <Route path="/contact" element={<Contact/>}/>
          <Route path="/cart" element={<Cart/>}/>
          <Route path="/" element={<PrivateRoute publicPage />}>
            <Route path='/login' element={ <LoginPage />}/>
            <Route path='/register' element={ <Register />}/>
          </Route>
        </Routes>
      </Router>
      <Toaster position='botton-center'/>
    </React.Fragment>
  )
}

export default App
