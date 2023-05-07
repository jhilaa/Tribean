import React from 'react';
import {useState, useEffect} from 'react'
import axios from 'axios';
import {Link, useParams} from "react-router-dom"
import {useNavigate} from "react-router-dom"
import CheckboxButtonsGroup from "./CheckboxButtonsGroup"

import "./AddResource.scss"


export function AddResource() {
    const [selectedTags, setSelectedTags] = useState([]);
    const history = useNavigate();

    const onSubmit = (e) => {
        e.preventDefault();
        const form = new FormData(e.target);
        console.log("formdata");
        console.log(form.get("title"));
        console.log(form.get("description"));
        console.log(selectedTags);
            axios.post("/resources/", {
                "id":0,
                "title":form.get("title"),
                "description":form.get("description"),
                "tagIds":selectedTags.map(tag => { return tag.id})}
            )
                .then(() => history("/home"))
    }

    const handleChange = (e) => {
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
                        <Link to="/home">
                            <input type="button" value='Annuler' className="btn btn-primary"></input>
                        </Link>
                    </div>
                </div>
            </form>
        </div>
    )
}