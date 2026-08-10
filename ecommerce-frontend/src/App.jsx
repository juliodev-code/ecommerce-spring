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
import Checkout from './components/checkout/Checkout'
import PaymentConfirmation from './components/checkout/PaymentConfirmation'
import AdminLayout from './components/admin/AdminLayout'
import Dashboard from './components/admin/dashboard/Dashboard'
import AdminProducts from './components/admin/products/AdminProducts'
import Sellers from './components/admin/sellers/Sellers'
import Category from './components/admin/categories/Category'
import Orders from './components/admin/orders/Orders'


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
        
          {/* Session pages  */}
          <Route path="/" element={<PrivateRoute/>}>
             <Route path="/checkout" element={<Checkout/>}/>
             <Route path='/order-confirm' element={ <PaymentConfirmation />}/>
          </Route>

          {/* Public pages  */}
          <Route path="/" element={<PrivateRoute publicPage />}>
            <Route path='/login' element={ <LoginPage />}/>
            <Route path='/register' element={ <Register />}/>
          </Route>

          <Route path='/' element={<PrivateRoute adminOnly/>}>
            <Route path='/admin' element={ <AdminLayout />}>
              <Route path='' element={<Dashboard />} />
              <Route path='products' element={<AdminProducts />} />
              <Route path='sellers' element={<Sellers />} />
              <Route path='orders' element={<Orders />} />
              <Route path='categories' element={<Category />} />
            </Route>
          </Route>
        </Routes>
      </Router>
      <Toaster position='botton-center'/>
    </React.Fragment>
  )
}

export default App
