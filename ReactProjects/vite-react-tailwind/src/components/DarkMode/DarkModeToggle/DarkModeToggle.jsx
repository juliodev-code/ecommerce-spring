import { useState, useEffect } from "react";

function DarkModeToggle(){
    const [isDarkMode, setIsDarkMode] = useState(()=>{return localStorage.getItem('theme') == 'dark'});
    
    useEffect(()=>{
        if(isDarkMode){
            document.documentElement.classList.add('dark')
            localStorage.setItem('theme','dark')
        }
        else{
            document.documentElement.classList.remove('dark')
            localStorage.setItem('theme','light')
        }
    }, [isDarkMode])
    return(
        <div>
            <button 
                className="p-2 dark:bg-gray-900 dark:text-white bg-gray-200 text-gray-800 rounded"
                onClick={()=>setIsDarkMode(!isDarkMode)}>
                Dark Mode
            </button>
        </div>
    );
}
export default DarkModeToggle;