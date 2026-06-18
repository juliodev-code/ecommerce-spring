import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import PostForm from './components/PostForm/PostForm'
import './index.css'
//import App from './App.jsx'


createRoot(document.getElementById('root')).render(
  <StrictMode>
    {/*<<App /> */}
    <PostForm/>
  </StrictMode>,
)
