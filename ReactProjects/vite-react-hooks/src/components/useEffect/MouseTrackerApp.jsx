import { useState } from "react";
import MouseTracker from "./MouseTracker/MouseTracker";

function MouseTrackerApp() {
  const [showComponent, setShowComponent] = useState(true);
  const toggleComponent = () => {
    setShowComponent((prev) => !prev);
  };
  return (
    <div>
      <button onClick={toggleComponent}>
        {showComponent ? "Unmount component" : "Mount Component"}
      </button>
      {
        showComponent && <MouseTracker/>
      }
    </div>
  );
}

export default MouseTrackerApp;
