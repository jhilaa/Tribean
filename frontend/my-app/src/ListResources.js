import React from 'react';
import "./ListResources.scss";
import {Resource} from "./Resource";

/*** version à base de fonction ************************/
export function ListResources ({listResources}) {
    React.useEffect(() => {}, []);

    return(
        <div>
            <h1>Ressources</h1>
            <div className="list-container">
                {listResources.map ((resource) =>
                <Resource key={resource.id} resourceId={resource.id} title={resource.title} description={resource.description} tags={resource.tagResponseDtoList}/>)}
            </div>
        </div>)
}


