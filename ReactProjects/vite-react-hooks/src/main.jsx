import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import HTMLReference from './components/useRef/HtmlReference/HTMLReference'
import CustomHooks from './components/customHooks/CustomHooks'
//import PropDrilling from './components/useContext/PropDrilling/PropDrilling'
//import './index.css'
//import UseRef from './components/useRef/UseRef'
//import DigitalClock from './components/useEffect/DigitalClock/DigitalClock'
//import App from './App.jsx'
/*import MultipleCounter from './components/MultipleCounter/MultipleCounter.jsx'
import FormExample from './components/FormExample/FormExample.jsx'
import ColorPicker from './components/ColorPicker/ColorPicker.jsx'
import UseEffect from './components/useEffect/UseEffect.jsx'*/
//import MouseTracker from './components/useEffect/MouseTracker/MouseTracker.jsx'
//import MouseTrackerApp from './components/useEffect/MouseTrackerApp.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    {/* <App />
    <MultipleCounter/> 
    <FormExample/>
    <ColorPicker/>
    <UseEffect/>
    <MouseTrackerApp/>
    <DigitalClock/>
    <UseRef/>
    <HTMLReference/>
    <PropDrilling/>*/}
    <CustomHooks/>
  </StrictMode>,
)
