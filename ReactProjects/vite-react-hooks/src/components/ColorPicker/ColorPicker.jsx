import React, { useState } from 'react'
import './ColorPicker.css'

function ColorPicker(){

    const [backgroundColor,setBackgroundColor] = useState('#ffffff')
    const colors = ['#FFF275','#FF8C42', '#FF3C38','#A23E48','#6C8EAD'];
    const handleColorChange = (color)=>{
        setBackgroundColor(color)
    }
    return (
        <div className="app-container" style={{backgroundColor: backgroundColor}}>
            <h1>ColorPicker</h1>
            <div className="color-palette">
                {
                    colors.map((color, index)=>
                        <div 
                        key={index} 
                        className="color-box"
                        style={{backgroundColor: color}}
                        onClick={()=>{handleColorChange(color)}}/>
                    )
                }
            </div>

            <div className="custom-color-picker">
                <input 
                    type="color" 
                    value={backgroundColor} 
                    onChange={(e)=>{handleColorChange(e.target.value)}}/>
            </div>
        </div>
    )
}

export default ColorPicker;