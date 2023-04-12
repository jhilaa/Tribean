import React from 'react';
import {Resource} from "./Resource";

export class ListResources extends React.Component {
	// init du state obligatoire
	constructor() {
        super();
		this.state = {
			resources : []
		}
	}

    books = [];

    // mise à jour du states
    componentDidMount() {
        //TODO charger les ressources
		this.setState({
			resources : [
            {id:1, title:"php"},
            {id:2, title:"regex"}
			]
		});
    }

    render() {
        if (this.state.resources.length == 0) {
            return "Aucune ressource enregistrée"
        }
        else {
            return this.state.resources.map(item => {
                return <Resource title={item.title} />});
        }
    }
}
