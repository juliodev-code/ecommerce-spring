import React, {useRef} from 'react'
import './HTMLReference.css'

function HTMLReference(){
    const inputRef = useRef(null);
    const inputRefNext = useRef(null);

    const focusInput = () => {
        inputRef.current.focus();
        inputRef.current.style.backgroundColor = 'yellow'
    }

    const focusInputNext = () => {
        inputRefNext.current.focus();
        inputRefNext.current.style.backgroundColor = 'yellow'
    }

    const resetFocus = () => {
        inputRefNext.current.style.backgroundColor = 'white'
        inputRef.current.style.backgroundColor = 'white'
    }

    return(
        <div>
            <h1>HTMLReference</h1>
            <input 
                type="text" 
                placeholder='Focus me'
                ref={inputRef} />
            <button onClick={focusInput}>Focus and highlight</button>

            <input 
                type="text" 
                placeholder='Focus me'
                ref={inputRefNext} />
            <button onClick={focusInputNext}>Focus and highlight</button>

            <button onClick={resetFocus}>Reset</button>
        </div>
    )
}

export default HTMLReference;