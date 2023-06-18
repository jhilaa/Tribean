import React from 'react';
import {useState, useEffect} from 'react'
import axios from 'axios';
import {Link, useLocation, useParams} from "react-router-dom"
import {useNavigate} from "react-router-dom";
import "./AddResource.scss";


export function AddResource() {
    const {resourceId} = useParams();
    const [title, setTitle] = useState('*');
    const [description, setDescription] = useState('*');
    const [tags, setTags] = useState([]);
    const history = useNavigate();
    const {align, design, width, length, icon} = {
        design: "button-style",
        align: "horizontal",
        width: 350,
        length: {tags}.length,
        icon: true
    };

    useEffect(() => {
            if (resourceId) {
                let request = "/resources/"+resourceId
                axios.get(request) // {id title description liste de TagResponseDto}
                    .then(response => {
                        setTitle(response.data.title);
                        setDescription(response.data.description);
                        document.getElementById("inputTitle").value = response.data.title;
                        document.getElementById("inputDescription").value = response.data.description;
                        let resourceSelectedTagIds = response.data.tagResponseDtoList.map((tag,i) => {return tag.id});
                        axios.get("/tags/all")
                            .then(response => {
                                setTags(response.data.map(tag => ({...tag, selected: resourceSelectedTagIds.indexOf(tag.id)>-1})));
                            })
                    })
            }
            else {
                axios.get("/tags/all")
                    .then(response => {
                        setTags(response.data.map(tag => ({...tag, selected: false})));
                    })
            }
    }, []);

    const handleTitleChange = (e) => {
        setTitle(e.target.value);
    };

    const handleDescriptionChange = (e) => {
        setDescription(e.target.value);
    };

    const handleTagChange = (tagName, index) => {
        //tags[index].selected = !tags[index].selected;
        let data = [...tags];
        console.log("handleTagChange ----");
        console.log(data[index].selected);
        console.log(data[index].checked);
        data[index].selected = !data[index].selected;
        data[index].checked = !data[index].checked;
        setTags([...data]);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const data = {
            "id" : resourceId,git add
            "title": title,
            "description": description,
            "tagIds": tags.filter(tag => tag.selected).map(tag => tag.id)
        };
        if (resourceId) {
            axios.put("/resources/1", {...data})
                .then(() => history("/home"))
        } else {
            axios.post("/resources/", {...data})
                .then(() => history("/home"))
        }
    }

    return (
        <div className="container-add-resource">
            <h1> Ajouter une ressource </h1>

            <form onSubmit={handleSubmit}>
                <div>
                    <label> Title:</label>
                    <input id="inputTitle" name="title" type="text" className="form-control"
                           onChange={handleTitleChange}></input>

                    <label> Description:</label>
                    <input id="inputDescription" name="description" type="text" className="form-control"
                           onChange={handleDescriptionChange}></input>

                    <label> Tags:</label>
                    <div>
                        <div
                            className={`custom-form-control  ${design}  ${align}  ${length >= 2 ? "first-last" : "no-list"}`}
                            style={{width: width}}>
                            {tags &&
                                tags.map((val, i) => {
                                    return (
                                        <div key={val.id} className="checkbox-list">
                                            <input
                                                type="checkbox"
                                                name={val.name}
                                                id={val.id}
                                                checked={val.selected}
                                                onChange={event => handleTagChange(val.name, i)}
                                            />
                                            <label htmlFor={val.id}>{val.name}</label>
                                        </div>
                                    );
                                })}

                            <br/>
                        </div>
                        <div className="container-submit">
                            <input type="submit" value='Valider' className="btn btn-primary"></input>
                            <Link to="/home">
                                <input type="button" value='Annuler' className="btn btn-primary"></input>
                            </Link>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    );
}
;
