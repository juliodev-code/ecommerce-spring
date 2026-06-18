import { useEffect, useState } from 'react'
import axios from 'axios'
import './App.css'

function App() {

  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)

  
  useEffect(()=>{
    setLoading(true)
    axios.all([
      axios.get('https://jsonplaceholder.typicode.com/posts'),
      axios.get('https://jsonplaceholder.typicode.com/users')
    ])
    .then(axios.spread((posts, users) => {
        console.log(posts)
         console.log(users)
        setData(posts.data)
        setLoading(false)
        //throw new Error('Something were wrong')
      }))
      .catch((error)=>{
        console.error("Error fetching data:" + error)
        setError("Error fetching data")
        setLoading(false)
      })
  },[])

  if(loading){
    return <h1>Loading...</h1>
  }

  if(error){
    return <h1>{error}</h1>
  }

  return (
    <div>
      <h2>API</h2>
      <ul>
        {data.map((post)=>
          <li key={post.id}>
            <p><strong>{post.title}</strong></p>
            <p>{post.body}</p>
          </li>
        )}
      </ul>
    </div>
  )
}

export default App
