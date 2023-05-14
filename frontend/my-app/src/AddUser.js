import React from 'react'
import {Link, useNavigate} from "react-router-dom";

import './AddUser.scss'
import axios from "axios";
import { AUTH_TOKEN_KEY } from "./App";

export class AddUser extends React.Component {

    constructor() {
        super();
        this.state = {userData: {}}
    }

    handleChange = (event) => {
        let currentState = {...this.state.userData};
        currentState[event.target.name] = event.target.value;
        this.setState({userData: currentState})
    }

    onSubmit = (event) => {
        console.log("submit ----------------");
        event.preventDefault();
        axios.post('/user', {
            ...this.state.userData
        }).then(response => {
            console.log("response : " + response);
            console.log("response.headers : " + response.headers);
            console.log("response.headers.authorization : " + response.headers.authorization);

            const bearerToken = response?.headers?.authorization;
            if (bearerToken && bearerToken.slice(0, 7) === 'Bearer ') {
                const jwt = bearerToken.slice(7, bearerToken.length);
                console.log("jwt : "+jwt);
                sessionStorage.setItem(AUTH_TOKEN_KEY, jwt)
            }
            this.props.setUserInfo(response.data.firstName + " " + response.data.lastName);
            // redirection vers listBooks
            this.props.history("/home");
        })
    }

    render() {
        return (
            <div className="add-user-container">
                <div>
                    <h1>M'inscrire</h1>
                    <div>
                        <form onSubmit={this.onSubmit}>
                            <div>
                                <label>email</label>
                                <input name="email" type="text" className="form-control" onChange={this.handleChange}/>
                            </div>
                            <div>
                                <label>nom</label>
                                <input name="lastname" type="text" className="form-control"
                                       onChange={this.handleChange}/>
                            </div>
                            <div>
                                <label>prenom</label>
                                <input name="firstname" type="text" className="form-control"
                                       onChange={this.handleChange}/>
                            </div>
                            <div>
                                <label>password</label>
                                <input name="password" type="password" className="form-control"
                                       onChange={this.handleChange}/>
                            </div>
                            <div className="container-valid text-center">
                                <input type="submit" value="Valider" className="btn btn-primary"
                                       onChange={this.handleChange}/>
                            </div>
                        </form>
                    </div>
                    <div><Link to="home">Retour à l'accueil</Link></div>
                </div>
            </div>
        )
    }
}
//wrap and export
    export default function (props) {
    const history = useNavigate();
    return <AddUser {...props} history = {history} />
}
