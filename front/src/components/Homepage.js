import React from 'react';
import { useState } from 'react';
import axios from 'axios';
import "../homepage.css";
import "../../node_modules/bootstrap/dist/css/bootstrap.min.css";

function Homepage(){
    const [file, setFile] = useState();

    return(
        <>
            <div className='container mt-5'>
                <div className='row justify-content-center'>
                    <div className='col-md-6 center'>
                        <form  onSubmit={handleSubmit} className="file-upload">
                        <h1>React File Upload</h1>
                        <input type="file" onChange={handleChange}/>
                        <button className='btn btn-primary' type="submit">Upload</button>
                        </form>
                        <ul className='mt-3' id="result">

                        </ul>
                    </div>
                </div>
                
            </div>
        </>
    );

    function handleChange(event) {
        setFile(event.target.files[0])
    }
    
    function handleSubmit(event) {
        event.preventDefault()
        const url = 'http://localhost:8080/uploadFile';
        const formData = new FormData();
        formData.append('file', file);
        formData.append('fileName', file.name);
        const config = {
        headers: {
            'content-type': 'multipart/form-data',
        },
        };
        axios.post(url, formData, config).then((response) => {
            document.getElementsByClassName("file-upload")
            var str_array = JSON.stringify(response.data.message).split(",")
            document.getElementById("result").innerHTML = ""

            for(var i = 0; i < str_array.length-1; i++) {
                console.log(str_array[i])
                var result = document.getElementById("result")
                var li = document.createElement("li")
                li.appendChild(document.createTextNode(str_array[i].replace('"',"")))
                result.appendChild(li)
            }
        });

    }
}

export default Homepage;