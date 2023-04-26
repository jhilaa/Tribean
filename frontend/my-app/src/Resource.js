import React from 'react';
import {Link} from "react-router-dom";
import "./Resource.scss"

export class Resource extends React.Component {

    constructor() {
        super();
    }

    // props en paramètre implicite
    render() {
        return (
            <div className="resource-container">
                <div>Titre : {this.props.title}</div>
                <div>{this.props.description}</div>
                <div>{this.props.tags.map((tag) => (<span className="myresource-container" key={tag.name}>{tag.name}
                </span>))}</div>
                <div className="container-button">
                    <Link to={`/addResource/${this.props.id}`}>
                        <button className="btn btn-primary btn-sm">Modifier</button>
                    </Link>
                    <button className="btn btn-primary btn-sm">Supprimer</button>
                </div>
            </div>
        )
    }
}

