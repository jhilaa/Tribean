import React from 'react';
import {useState, useEffect} from 'react'
import axios from 'axios';
import {Link, useLocation, useParams} from "react-router-dom"
import {useNavigate} from "react-router-dom"
import CheckboxButtonsGroup from "./CheckboxButtonsGroup"

import "./AddResource.scss"

export function AddResource() {
    const { resourceId } = useParams();
    const [resourceData, setResourceData] = useState({title:"", description:""});
    const [tagsData, setTagsData] = useState([]);
    const [selectedTags, setSelectedTags] = useState([]);
    const [showSpinner, setShowSpinner] = useState(false);
    const history = useNavigate();

   React.useEffect(() => {
       if (resourceId) {


        axios.get("/resources").then(response => {
                setTagsData(response.data)
            setResourceData({
                title: "",
                description: "",
            });
            }).then(() => {
                setShowSpinner(!showSpinner)
            });
        }
    }, []);


    const onSubmit = (e) => {
        e.preventDefault();
        const form = new FormData(e.target);
        const data = {
            "id": 0,
            "title": form.get("title"),
            "description": form.get("description"),
            "tagIds": selectedTags.map(tag => {
                return tag.id
            })
        };

        if (props.idResource) {
            axios.put("/resources/", {...data})
                .then(() => history("/home"))
        } else {
            axios.post("/resources/", {...data})
                .then(() => history("/home"))
        }
    }

    const handleChange = (e) => {
    }

    return (
        <div className="container-add-resource">
            <h1> Ajouter une ressource </h1>

            <form onSubmit={onSubmit}>
                <div>
                    <label> Intitulé de la resource</label>
                    <input name="title" type="text" onChange={handleChange} className="form-control">{resourceId?resourceData.title:""}</input>
                    <label> Description</label>
                    <input name="description" type="text" onChange={handleChange} className="form-control"></input>
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