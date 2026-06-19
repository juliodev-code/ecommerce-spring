import React, { useState } from "react";
import "./DarkMode.css";
import DarkModeToggle from "./DarkModeToggle/DarkModeToggle";

function DarkMode() {
  return (
    <div className="p-4 bg-white min-h-screen dark:bg-gray-900 dark:text-white">
        <DarkModeToggle/>
        <h1 className="text-2xl">Welcome to Dark Mode App</h1>
        <p>This is an example of toggle light and dark mode</p>
    </div>
    )
}

export default DarkMode;