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
                {this.props.title}
                <div className="container-button">
                    <Link to={`/addResource/${this.props.id}`}>
                        <button>Modifier</button>
                    </Link>
                    <button>Supprimer</button>
                </div>
            </div>
        )
    }
}

