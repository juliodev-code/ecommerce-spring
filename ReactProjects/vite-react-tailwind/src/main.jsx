import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import DarkMode from './components/DarkMode/DarkMode'
//import App from './App.jsx'
//import ResponsiveDesign from './components/ResponsiveDesign/ResponsiveDesign.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    {/*<App />
    <ResponsiveDesign/>*/}
    <DarkMode/>
  </StrictMode>,
)
