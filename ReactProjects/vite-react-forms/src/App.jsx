import { useState } from 'react'
import './App.css'

function App() {  
  const [formData, setFormData] = useState({
    name: '',
    email:''
  });

  const [errors, setErrors] = useState({});

  const validate = ()=>{
    const newErrors = {}
    if(!formData.name.trim()){
      newErrors.name = 'Name is required'
    }

    return newErrors;
  }

  const handleSubmit = (e) =>{
    e.preventDefault();
    const validationErrors = validate();
    if(Object.keys(validationErrors).length > 0){
      setErrors(validationErrors)
    }
    else{
      console.log("Form data submitted!", formData)
    }
    
  }
  const handleChange = (ev) =>{
    const {name, value} = ev.target
    console.log(ev.target.value)
    setFormData({...formData, [ev.target.name]: ev.target.value})
    if(errors[name]){
      const newErrors = {...errors}
      delete newErrors[name]
      setErrors(newErrors)
    }
    
  }

  return (
    <div>
      <h1>Forms in React</h1>
      <form onSubmit={handleSubmit}>
        <label>
          Name:
          <input 
            type="text" 
            name="name" 
            value={formData.name} 
            onChange={handleChange}/>
          {errors.name && <span style={{color:'red'}}>{errors.name}</span>}
        </label>
        <br/>
        <label>
          Email:
          <input 
            type="email" 
            name="email" 
            value={formData.email} 
            onChange={handleChange}/>
        </label>
        <button type="submit">Submit</button>
      </form>
    </div>
  )
}

export default App
