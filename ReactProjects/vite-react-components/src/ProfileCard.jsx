function ProfileCard({name, age, isMember, hobbies, onHobbyClick}){
    return (
        <div className="profile-card">
            <h3>Name:{name}</h3>
            <h3>Age:{age}</h3>
            <h3>is member:{isMember ? "yes": "no"}</h3>
            <h3>Hobbies:</h3>
            <ul>
                {hobbies.map((hobby, index)=> {
                    return <li 
                            key={index} 
                            onClick={() => onHobbyClick(hobby)}>{hobby}</li>
                })}
            </ul>
        </div>
    )
}

export default ProfileCard;