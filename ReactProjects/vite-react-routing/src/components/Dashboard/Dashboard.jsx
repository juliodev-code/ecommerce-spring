import { useNavigate } from "react-router-dom";

function Dashboard(){
    const navigate = useNavigate();
    const handleLogout = () =>{
        navigate('/')
    }

    return(
        <div className="flex flex-col items-center justify-center min-h-screen bg-gray-50">
            <h2 className="text-3xl font-bold text-blue-600">Welcome</h2>
            <button className="text-gray-900 mt-4 text-lg bg-green-400 p-4" onClick={handleLogout}>Logout</button>
        </div>
    )
}
export default Dashboard;