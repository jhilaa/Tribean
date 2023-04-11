import React from 'react';
import {Resource} from './Resource';

export const Resources = () => {

    const resources = [
        {id:1, title:"php"},
        {id:2, title:"regex"}
    ];

    if (resources.length == 0) {
        return "Aucune ressource enregistrée"
    }
    else {
        return resources.map(item => {
            return <Resource title={item.title} />});
    }
}

