import React from 'react'
import { Link, Outlet } from 'react-router-dom';

function About(){
    return(
        <div className="flex flex-col items-center justify-center min-h-screen bg-gray-50">
            <h2 className="text-3xl font-bold text-blue-600">About Page</h2>
            <p className="text-gray-700 mt-4 text-lg">Welcome to About page. Explore this site to learn more.</p>
            <nav className="mt-6">
                <ul className="flex space-x-4">
                    <li><Link to="team" className="text-blue-500 hover:underline">Our Team</Link></li>
                    <li><Link to="/" className="text-blue-500 hover:underline">Back to home</Link></li>
                </ul>
            </nav>
            <div className="mt-8 w-full max-w-4xl">
                <Outlet/>
            </div>
        </div>
    )
}

export default About;