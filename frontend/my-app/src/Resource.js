import React from 'react';
import {Link} from "react-router-dom";
import "./Resource.scss"

export function displayResource(title, description, tags) {

    return (
        <div className="resource-container">
            <div>Titre : {title}</div>
            <div>{description}</div>
            <div>{tags?.map((tag) => (<span className="myresource-container" key={tag.name}>{tag.name}
                </span>))}</div>
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
        </div>
    )
}

