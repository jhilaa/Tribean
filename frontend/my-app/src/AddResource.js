import React from 'react';
import { useState, useEffect } from 'react'
import axios from 'axios';
import { useParams } from "react-router-dom"
import { useNavigate } from "react-router-dom"
import "./AddResource.scss"


export function AddResource() {
    let {resourceId} = useParams();
    const [resourceData, setResourceData] = useState({name: "", tagId: 1});
    const [tagsData, setTagsData] = useState([]);
    const history = useNavigate();

    React.useEffect(() => {
        axios.get("/tags").then(response => {
            setTagsData(response.data)
        })
    }, [])

    const onSubmit = (event) => {
        event.preventDefault();
        if (bookId) {
            axios.put("/books/${bookId}"), {
                ...bookData  // stocké dans le stateS
            }
            .then(() => history("/myResources"))
        } else {
            // création
            axios.post("/resources", {
                ...bookData  // stocké dans le stateS
            })
                .then(() => {
                    history("/myResources")
                })
        }
    }

    const handleChange = (event) => {
        let currenState = {...resourceData};
        currenState[event.target.name] = event.target.value;
        setResourceData(currenState)
    }

    return (
    <div className="container-add-resource">
    <h1> Ajouter une ressource </h1>
    <form onSubmit={onSubmit}>
        <div>
            <label> Intitulé de la resource</label>
            <input name="name" type="test" onChange={handleChange} className="form-control"></input>
            <label> Tags</label>
            <div>
                <select name="tagId" onChange={handleChange} className="form-control">
                    {tagsData.map(tag => (
                        <option key={tag.id} value={tag.id}>{tag.label}</option>
                    ))}
                </select>
            </div>
            <div className="container-submit">
                <input type="submit" value='Valider' className="btn btn-primary"></input>
            </div>

        </div>

    </form>
    </div>
)
}