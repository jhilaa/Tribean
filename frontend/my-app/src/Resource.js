import React from 'react';
import {createSearchParams, Link} from "react-router-dom";
import "./Resource.scss"

export function Resource (props) {

    return (
        <div key={props.title} className="resource-container">
            <div>Titre : {props.title}</div>
            <div>{props.description}</div>
            <div>{props.tagResponseDtoList?.map((tag) => (
                <span key={tag.id} className="badge badge-primary">{tag.name}</span>))}
            </div>
            <div className="container-button">
                <Link to={`/addResource/${props.id}`}
                >
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

