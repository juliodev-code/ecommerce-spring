import React from 'react'
import { useLocation } from 'react-router-dom';
function CurrentLocation(){
    const location = useLocation();

    return(
        <div>Current Path: {location.pathname}</div>
    )
}

export default CurrentLocation;