import React from 'react';
import {Resource} from './Resource';
import {Link} from "react-router-dom";
import "./ListResources.scss";

export class ListResources extends React.Component {
    // init du state
    constructor() {
        super();
        this.state = {
            resources: []
        }
    }

    // mise à jour du states
    componentDidMount() {
        //TODO charger les ressources
        this.setState({
            resources: [
                {id: 1, title: "php"},
                {id: 2, title: "regex"}
            ]
        });
    };

    render() {
        return (
            <div className="container">
                <h1>Mes Ressources</h1>
                <div className="list-container">
                    {this.state.resources.length === 0 ? "Aucune resource enregistrée" : null}
                    {this.state.resources.map((resource, key) =>
                        <div key={key} >
                            {  <Resource id={resource.id} title={resource.title}></Resource>}
                        </div>)}
                </div>
                <Link to="/addBook">
                    <button>Nouvelle resource</button>
                </Link>
            </div>)
    }
}