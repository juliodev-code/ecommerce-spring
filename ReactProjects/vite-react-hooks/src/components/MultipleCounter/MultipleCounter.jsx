import './MultipleCounter.css'
import React, {useState} from 'react';

function MultipleCounter(){
    const [counters, setCounter] = useState([{id : 1, value: 0}])

    const addCounters = () => {
        setCounter([...counters, {id : counters.length + 1, value: 0}])
    }

    const incrementCounter = (id) => {
        setCounter(counters.map(counter => counter.id === id ? {...counter, value: counter.value + 1} : counter ))
    }
    return (
        <div className="app-container">
            <h1>Multiple Counters!</h1>
            <button onClick={addCounters}>Add Counter</button>
            <ul>
                {counters.map(counter => 
                    <li key={counter.id}>
                        Counter {counter.id} : {counter.value}
                        <button onClick={() => {incrementCounter(counter.id)}}>increment</button>
                    </li>)}
            </ul>
        </div>
    )
}

export default MultipleCounter;