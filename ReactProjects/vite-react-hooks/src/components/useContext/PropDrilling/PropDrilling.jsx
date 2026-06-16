import React, { createContext, useContext, useState } from 'react'


const ThemeContext = createContext('light');
function PropDrilling(){
    
    //const theme = 'dark';
    const [theme, setTheme] = useState('light');
    const toggleTheme = ()=>{
        setTheme(prevTheme => (prevTheme==='light' ? 'dark': 'light'));
    }

    return (
        <ThemeContext.Provider value={theme}>
            <div style={{border:'2px solid black', padding: '20px'}}>
                <h2>Parent</h2>
                <button onClick={toggleTheme}>Toggle Theme</button>
                <ComponentA/>
            </div>
        </ThemeContext.Provider>
    );
}

function ComponentA(){
    return(
        <div style={{border:'2px solid black', padding: '20px'}}>
            <h2>Component A</h2>
            <ComponentB/>
        </div>
    )
}

function ComponentB(){
    return(
        <div style={{border:'2px solid black', padding: '20px'}}>
            <h2>Component B</h2>
            <ThemedComponent/>
        </div>
    )
}

function ThemedComponent(){
    const theme = useContext(ThemeContext);
    return(
        <div style={{border:'2px solid black', padding: '20px'}}>
            <h2>ThemedComponent</h2>
            The theme component is: {theme}
        </div>
    )
}

export default PropDrilling;