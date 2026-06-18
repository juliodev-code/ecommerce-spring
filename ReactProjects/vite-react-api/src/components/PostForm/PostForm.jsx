import React, { useState } from 'react';
import api from '../../api/api'

import './PostForm.css'

function PostForm(){
    const [data, setData] = useState();

    const handleSubmit = (event)=>{
        event.preventDefault();
        const newPost = {
            title: 'foo',
            body: 'bar',
            userId: 1,
        }

        api.post('/posts', newPost)
        .then((response)=>{
            console.log('New post added:', response.data)
            setData([response.data])
        });
    }
    return(
        <div>
            Postform
            <form onSubmit={handleSubmit}>
                <button type="submit">Add Post</button>
            </form>
        </div>
    )
}

export default PostForm;