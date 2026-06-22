import React from 'react'
import { useNavigate } from 'react-router-dom';

function Home(){
    const navigate = useNavigate();
    const handleLogin = () =>{
        navigate('/dashboard')
    }
    return(
        <div className="flex flex-col items-center justify-center min-h-screen bg-gray-50">
            <h2 className="text-3xl font-bold text-blue-600">Home Page</h2>
            <p className="text-gray-700 mt-4 text-lg">Welcome to home page. Explore this site to learn more.</p>
            <button className="text-gray-900 mt-4 text-lg bg-green-400 p-4" onClick={handleLogin}>Login</button>
        </div>
    )
}

export default Home;