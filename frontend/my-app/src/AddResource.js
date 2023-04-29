import React from 'react';
import {useState, useEffect} from 'react'
import axios from 'axios';
import {useParams} from "react-router-dom"
import {useNavigate} from "react-router-dom"
import CheckboxButtonsGroup from "./CheckboxButtonsGroup"

import "./AddResource.scss"


export function AddResource() {
    let {resourceId} = useParams(null);
    const [selectedTags, setSelectedTags] = useState([]);
    const history = useNavigate();

    //React.useEffect(() => {setSelectedTags([])}, []);

    const onSubmit = (event) => {
        event.preventDefault();
        console.log ("selectedTags");
        console.log (selectedTags);
        /*
        if (resourceId) {
            axios.put("/resource/${resourceId}", {
                ...resourceData  // stocké dans le stateS
            })
                .then(() => history("/myResources"))
        } else {
            // création
            axios.post("/resources/add", {
                ...resourceData  // stocké dans le stateS
            })
                .then(() => {
                    history("/myResources")
                })
        }
        */

    }

    const handleChange = (event) => {
        console.log ("selectedTags");
        console.log (selectedTags);
    }

    return (
        <div className="container-add-resource">
            <h1> Ajouter une ressource </h1>
            <form onSubmit={onSubmit}>
                <div>
                    <label> Intitulé de la resource</label>
                    <input name="title" type="test"  onChange={handleChange} className="form-control"></input>
                    <label> Description</label>
                    <input name="description" type="test"  onChange={handleChange} className="form-control"></input>
                    <label> Tags</label>
                    <div>
                        <CheckboxButtonsGroup
                         setSelectedTags={setSelectedTags}/>
                    </div>
                    <div className="container-submit">
                        <input type="submit" value='Valider' className="btn btn-primary"></input>
                    </div>
                </div>
            </form>
        </div>
    )
}