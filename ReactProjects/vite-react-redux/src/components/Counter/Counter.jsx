import React from 'react'
import {useDispatch, useSelector} from 'react-redux'
import { decrement, increment } from '../../store/actions/action';
import { useEffect } from 'react';
import { fetchPostRequest, fetchPosts } from '../../../../vite-redux-project/src/store/actions/postActions';
function Counter(){
    const count = useSelector((state => state.counter.count))
    const dispatch = useDispatch();

    useEffect(()=>{
        dispatch(fetchPosts())
    },[dispatch])
    return(
        <div>
            <h1>Count in counter component:{count}</h1>
            <button onClick={()=>dispatch(increment())}>Increment</button>
            <button onClick={()=>dispatch(decrement())}>Decrement</button>
        </div>
    );
}

export default Counter;