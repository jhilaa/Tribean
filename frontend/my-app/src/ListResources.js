import React from 'react';
import {Link} from "react-router-dom";
import "./ListResources.scss";
import axios from "axios";
import Spinner from "react-bootstrap/Spinner";

/*** version à base de fonction ************************/
export function ListResources ({listResources}) {
    React.useEffect(() => {}, []);

    const displayResources = (listResources) => {
        return (
            listResources.map(resource => { return (
            <div key={resource.id} className="resource-container">
                <div>Titre : {resource.title}</div>
                <div>{resource.description}</div>
                <div>{resource.tags.map((tag)=>{return (tag.name)})}</div>
                <div className="container-button">
                    <Link to="/addResource/{id}">
                        <button type="button" className="btn">
                            <i className="bi bi-pencil-square"></i>
                        </button>
                    </Link>

                    <button type="button" className="btn">
                        <i className="bi bi-trash"></i>
                    </button>
                </div>
            </div>)}
            )
        )
    }

    return(
        <div>
            <h1>Ressources</h1>
            <div className="list-container">
                {displayResources(listResources)}
            </div>
        </div>)
}

export default ListResources;

