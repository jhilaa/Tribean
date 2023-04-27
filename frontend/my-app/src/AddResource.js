import React from 'react';
import {useState, useEffect} from 'react'
import axios from 'axios';
import {useParams} from "react-router-dom"
import {useNavigate} from "react-router-dom"
import CheckboxButtonsGroup from "./CheckboxButtonsGroup";
import "./AddResource.scss"


export function AddResource() {
    let {resourceId} = useParams();
    let init = ([{
        "id": 2,
        "name": "js",
        "color": "red",
        "resources": []
    },
        {
            "id": 3,
            "name": "regex",
            "color": "blue",
            "resources": []
        }]);
    const [resourceData, setResourceData] = useState({title: "", tagId: 1, description: ""});
    const [tagsData, setTagsData] = useState(init);
    const history = useNavigate();

    React.useEffect(() => {
        console.log("useEffect3");
        let sample = ([{
                    "id": 2,
                    "name": "js",
                    "color": "red",
                    "resources": []
                },
                {
                    "id": 3,
                    "name": "regex",
                    "color": "blue",
                    "resources": []
                }]);
        console.log("sample");
        setTagsData(sample);
        console.log("tagsData");
        console.log(tagsData);
    }, []);
    /*
    axios.get("/tags/all").then(response => {
        setTagsData(response.data);
        console.log("---------------");
        console.log(response.data);
        console.log(tagsData);
    })
}, [])*/

    const onSubmit = (event) => {
        event.preventDefault();
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
                    <input name="title" type="test" onChange={handleChange} className="form-control"></input>
                    <label> Description</label>
                    <input name="description" type="test" onChange={handleChange} className="form-control"></input>
                    <label> Tags</label>
                    <div>
                    <CheckboxButtonsGroup
                        checkboxObj={tagsData}
                        //design: button, tile, default
                        commonStyle={{
                            design: "button-style",
                            align: "horizontal",
                            width: 350,
                            length: {tagsData}.length,
                            icon: true
                        }} />
                    </div>
                    <div className="container-submit">
                        <input type="submit" value='Valider' className="btn btn-primary"></input>
                    </div>

                </div>

            </form>
        </div>
    )
}