import React from 'react'
import {useDispatch, useSelector} from 'react-redux'
import { decrement, increment } from '../../store/actions/action';
function Counter(){
    const count = useSelector((state => state.count))
    const dispatch = useDispatch();
    return(
        
        <div>
            <h1>Count in counter component:{count}</h1>
            <button onClick={()=>dispatch(increment())}>Increment</button>
            <button onClick={()=>dispatch(decrement())}>Decrement</button>
        </div>
    );
}

export default Counter;