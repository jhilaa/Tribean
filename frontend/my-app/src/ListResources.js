import React from 'react';
import {Resource} from './Resource';
import {Link} from "react-router-dom";
import "./ListResources.scss";
import axios from "axios";

/*** version à base de fonction ************************/
export function ListResources () {
    const [listResources, setListResources] = React.useState([])

    React.useEffect(() => {
        axios.get("/resources/all").then(response => {
            setListResources(response.data)
        })
    }, []);


/*
    React.useEffect(() => {
        // TODO charger les ressources
        setListResources(
            [
                {
                    title: "asterix",
                    category: "BD",
                },

                {
                    title: "tintin",
                    category: "BD",
                }
            ]
        )
    }, []);
    */

    return (
        <div className="container">
            <h1>Ressources</h1>
            <div className="list-container">
                {listResources.length === 0 ? "Aucune ressource disponible" : null}
                {listResources.map((resource) => (<div className="myresource-container" key={resource.title}>
                    <Resource title={resource.title} tags={resource.tags}></Resource>
                </div>))}
            </div>
            <Link to="/addResource">
                <button className="btn btn-primary btn-sm">Nouveau livre</button>
            </Link>
        </div>)
}

/*** version à base de classe ************************
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
                <Link to="/addResource">
                    <button>Nouvelle resource</button>
                </Link>
            </div>)
    } ***************************/
