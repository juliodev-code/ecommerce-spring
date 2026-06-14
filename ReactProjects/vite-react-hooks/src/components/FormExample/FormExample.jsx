import React, {useState} from 'react';
import './FormExample.css'

function FormExample() {
    const [formData, setFormData] = useState({
        text:'Abcd',
        checkbox: false,
        radio: '',
        select:''
    });

    const handleChange = (e) => {
        const {name, value, type, checked} = e.target;
        setFormData({...formData, 
            [name]: type === 'checkbox' ? checked : value});

    }

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
                    <label>
                        Radio:
                        <input 
                            type="radio" 
                            name='radio' 
                            value='option1'
                            checked={formData.radio === 'option1'} 
                            onChange={handleChange}
                        />
                        Option 1

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
                {/* Dropdown field */}

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
                    <label>Select:
                        <select 
                            name="select" 
                            value={formData.select}
                            onChange={handleChange}>
                            <option value="">Choose an option</option>
                            <option value="option1">Option 1</option>
                            <option value="option2">Option 2</option>
                        </select>
                       
                    </label>
                    
                </div>

                <div className="form-data">
                    <h3>Form data:</h3>
                    <p><strong>Text:</strong>{formData.text || 'N/A'}</p>
                    <p><strong>Checkbox:</strong>{formData.checkbox ? 'Checked' : 'Unchecked'}</p>
                    <p><strong>Radio:</strong>{formData.radio || 'N/A'}</p>
                    <p><strong>Select:</strong>{formData.select || 'N/A'}</p>
                </div>
            </form>
        </div>
    )
}

export default FormExample;