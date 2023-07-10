import React, {useState} from 'react';
import "./ListResources.scss";
import {Resource} from "./Resource";
import Spinner from "react-bootstrap/Spinner";

/*** version à base de fonction ************************/
export function ListResources ({listResources}) {
    React.useEffect(() => {}, []);
    return(
        <div>
            <h1>Ressources</h1>
            <div className="list-container">
                {listResources.map ((resource) =>
                <Resource key={resource.id} {...resource} />)}
            </div>
        </div>)
}


