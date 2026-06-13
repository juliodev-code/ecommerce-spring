
import './App.css'
import Header from './components/Header'
import About from './components/About'
import Contact from './components/Contact'
import Footer from './components/Footer'
import ProjectList from './components/ProjectList'

 function WelcomeMessage(name){
    return <h2>Welcome {name}</h2>;
  }

  function Greeting(isMorning){
    if(isMorning){
      return <h1>Good Morning!</h1>
    }
    else {
      return <h1>Good Evening!</h1>
    }
  }

  function showAlert(condition,message){
    if(condition){
      return <div>{message}</div>
    }
    return null;
  }

function App() {
  const name = "Julio Jaramillo"
  const profession = "Full Stack Developer"
  const projects = [
    {title:"Project One", description:"A web application using React and Node JS", link: "#"},
    {title:"Project two", description:"An ecommerce platform using Java", link: "#"}
  ]

  return (
    <>
      <div className='App'>
        <Header/>
        <About />
        <ProjectList/>
        <Contact/>
        <Footer/>
      </div>
    </>
  )
}

export default App
