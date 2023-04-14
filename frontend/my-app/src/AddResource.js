import React from 'react';
import  {useState} from 'react'
import {useParams} from "react-router-dom"
import "./AddResource.scss"


export function AddResource() {
    const [resourceData, setResourceData] = useState({name: "", tagId: 1});

    let {resourceId} = useParams();
    if (resourceId) {
        return "updateResource";
    }

    const  tag= [
        {id:1, label:"regex"},
        {id:2, label:"php"},
        {id:3, label:"sql"},
    ]

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
    <h1> Ajouter une resource </h1>
    <form onSubmit={onSubmit}>
        <div>
            <label> Intitulé de la resource</label>
            <input name="name" type="test" onChange={handleChange} className="form-control"></input>
            <label> Tags</label>
            <div>
            <select name="tagId" className="form-control" onChange={handleChange}>
                {tag.map(category => (
                    <option value={category.id} key={category.id}>{category.label}</option>
                )) }
            </select>
            </div>
            <div className="container-submit">
                <input type="submit" value="Valider" className="btn btn-primary"></input>
            </div>

        </div>

    </form>
    </div>
)
}