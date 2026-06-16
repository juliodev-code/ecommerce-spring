import React, {useEffect, useState} from 'react';
import './UseEffect.css'
function UseEffect(){
    const [count, setCount] = useState(0);
    const [anotherValue, setAnotherValue] = useState(0);

    useEffect(()=>{
        document.title = `Count ${count}`;
        console.log("Use Effect triggered")
    },[]);
    const incrementCount = () =>{
        setCount(count + 1)
    };
    return(
        <div>
            <h1>UseEffect Hook</h1>
            <button onClick={incrementCount}>Increment</button>
            <button onClick={()=>{setAnotherValue(anotherValue + 1)}}>Increment</button>
        </div>
    )
}

export default UseEffect;