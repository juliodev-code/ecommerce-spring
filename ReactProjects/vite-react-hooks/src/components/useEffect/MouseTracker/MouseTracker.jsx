import React, {useState,useEffect} from 'react';
import './MouseTracker.css'

function MouseTracker(){
    const [mousePosition,setMousePosition] = useState({x:0, y:0});

    useEffect(()=>{
        const handleMouseMove = (event) =>{
            setMousePosition({x:event.clientX, y:event.clientY});
        }
        window.addEventListener('mousemove', handleMouseMove);
        console.log("mousemove event added!")
        return ()=>{
            window.removeEventListener('mousemove', handleMouseMove);
            console.log("mousemove event removed!")
        }   
    }, [])
    return (
        <div className="app-container">
            <h2>Mouse Tracker</h2>
            <p>X:{mousePosition.x}, Y: {mousePosition.y}</p>
        </div>
    );
}

export default MouseTracker;