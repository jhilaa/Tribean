import React from 'react';
import { useState, useEffect } from 'react'
import axios from 'axios';
import {useParams} from "react-router-dom"
import "./AddResource.scss"


export function AddResource() {
    let {resourceId} = useParams();
    const [resourceData, setResourceData] = useState({name: "", tagId: 1});
    const [tagsData, setTagsData] = useState([]);


    React.useEffect(() => {
        axios.get("/tags").then(response => {
            setTagsData(response.data)
        })
    }, [])

    const onSubmit = (event) => {
        event.preventDefault();
        console.log("onSubmit")
        console.log(resourceData)
        //TODO lien avec le back
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