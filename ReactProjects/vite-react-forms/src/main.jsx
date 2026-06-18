import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import FormLibrary from './components/FormLibrary/FormLibrary.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    {/*<App />*/}
    <FormLibrary/>
  </StrictMode>,
)
