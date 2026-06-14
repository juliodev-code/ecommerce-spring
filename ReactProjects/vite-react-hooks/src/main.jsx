import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import MultipleCounter from './components/MultipleCounter/MultipleCounter.jsx'
import FormExample from './components/FormExample/FormExample.jsx'
import ColorPicker from './components/ColorPicker/ColorPicker.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    {/* <App />
    <MultipleCounter/> 
    <FormExample/>*/}
    <ColorPicker/>
  </StrictMode>,
)
