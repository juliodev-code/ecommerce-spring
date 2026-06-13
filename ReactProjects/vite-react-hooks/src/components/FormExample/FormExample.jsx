import React, {useState} from 'react';
import './FormExample.css'

function FormExample() {
    const [formData, setFormData] = useState({
        text:'Abcd',
        checkbox: false,
        radio: '',
        select:''
    });

    const handleChange = (e) => {}

    return (
        <div className="form-container">
            <h1>Form Example</h1>
            <form action="">
                {/* text input */}
                <div className="form-field">
                    <label>Text:
                        <input 
                            type="text" 
                            name='text' 
                            value={formData.text} 
                            onChange={handleChange}
                        />
                    </label>
                </div>
                {/* checkbox */}
                <div className="form-field">
                    <label>
                         Checkbox:
                        <input 
                            type="checkbox" 
                            name='checkbox' 
                            value={formData.checkbox} 
                            onChange={handleChange}
                        />
                       
                    </label>
                    
                </div>

                {/* radiobutton */}
                <div className="form-field">
                    Radio:
                    <label>
                        <input 
                            type="radio" 
                            name='radio' 
                            value='option1'
                            checked={formData.radio === 'option1'} 
                            onChange={handleChange}
                        />
                        Option 1
                    </label>

                    <label>
                        <input 
                            type="radio" 
                            name='radio' 
                            value='option2'
                            checked={formData.radio === 'option2'} 
                            onChange={handleChange}
                        />
                        Option 2
                    </label>
                    
                </div>
            </form>
        </div>
    )
}

export default FormExample;