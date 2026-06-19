import React,{useState} from 'react'
import './ResponsiveDesign.css'
function ResponsiveDesign(){

    return(
        <div className="bg-gray-100 p-4">
            <ul className="list-none md:flex md:gap-4">
                <li className='bg-custom-blue text-white p-1 m-2 rounded-lg md:px-4'>Item 1</li>
                <li className='bg-custom-blue text-white p-1 m-2 rounded-lg'>Item 2</li>
                <li className='bg-custom-blue text-white p-1 m-2 rounded-lg'>Item 3</li>
            </ul>
        </div>
    )
}

export default ResponsiveDesign;