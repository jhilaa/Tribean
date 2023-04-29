import React from 'react';
import {Resource} from './Resource';
import {Link} from "react-router-dom";
import "./ListResources.scss";
import axios from "axios";

/*** version à base de fonction ************************/
export function ListResources2 () {
    const [listResources, setListResources] = React.useState([])
    React.useEffect(() => {
        axios.get("/resources/all").then(response => {
            setListResources(response.data)
        })
    }, []);

    return (

        <div className="container">
            <h1>Ressources</h1>
            <div className="list-container">
                {listResources.length === 0 ? "Aucune ressource disponible" : null}
                {listResources.map((resource) => (<div className="myresource-container" key={resource.title}>
                    <Resource title={resource.title} description={resource.description} tags={resource.tags}></Resource>
                </div>))}
            </div>
            <Link to="/addResource">
                <button className="btn btn-primary btn-sm">Nouveau livre</button>
            </Link>
        </div>)
}

