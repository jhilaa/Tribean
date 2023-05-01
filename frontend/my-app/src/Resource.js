import React from 'react';
import {Link} from "react-router-dom";
import "./Resource.scss"

export function Resource (props) {

    return (
        <div key={props.title} className="resource-container">
            <div>Titre : {props.title}</div>
            <div>{props.description}</div>
            <div>{props.tags?.map((tag) => (<span key={tag.id} className="myresource-container" >{tag.name}
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

