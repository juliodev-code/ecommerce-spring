
import './App.css'
import ProfileCard from './ProfileCard'



function App() {
    const handleHobbyClick = (hobbySelected) => {
      alert(`You clicked on ${hobbySelected}`);
    }

    const aliceProfile = {
      name :"Alice",
      age: "23", 
      isMember: true,
      onHobbyClick :handleHobbyClick,
      hobbies:['Reading', 'Cooking']
    }

    const julioProfile = {
      name :"Julio",
      age: "33", 
      isMember: false,
      onHobbyClick :handleHobbyClick,
      hobbies:['Music', 'Coding']
    }


    return (
      <div className='app-container'>
        <ProfileCard {...aliceProfile} />
        <ProfileCard {...julioProfile}/>
      </div>
    )
}

export default App
