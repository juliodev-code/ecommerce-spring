import React,{useState,useEffect, useRef} from 'react'
import './UseRef.css'

function UseRef(){
    const [stateCount, setStateCount] = useState(0);
    const refCount = useRef(0);
    const incrementStateCount = () => setStateCount(stateCount + 1);
    const incrementRefCount = () =>  {
        refCount.current += 1
        console.log(`Refcount: {refCount.current}`)
    };

    useEffect(()=>{
        console.log("Component re-rendered!")
    })
    
    return (
        <div>
            <p>State count: {stateCount}</p>
            <button onClick={incrementStateCount}>Increment state count</button>
            <p>Ref count: {refCount.current}</p>
            <button onClick={incrementRefCount}>Increment state count</button>
        </div>
    );
}

export default UseRef;