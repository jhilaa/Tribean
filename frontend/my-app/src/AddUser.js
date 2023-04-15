import React from 'react'
import { Link } from "react-router-dom";

import './AddUser.scss'

export class AddUser extends React.Component {

    constructor() {
        super();
        this.state = { userData: {}}
    }

    handleChange = (event) => {
        let currentState = { ...this.state.userData };
        currentState[event.target.name] = event.target.value;
        this.setState({ userData: currentState })
    }

    onSubmit = (event) => {
        event.preventDefault();
        console.log("onSubmit")
        console.log(this.state.userData)
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
                                <input name="email" type="text" className="form-control" onChange={this.handleChange} />
                            </div>
                            <div>
                                <label>nom</label>
                                <input name="lastName" type="text" className="form-control" onChange={this.handleChange} />
                            </div>
                            <div>
                                <label>prenom</label>
                                <input name="firstName" type="text" className="form-control" onChange={this.handleChange} />
                            </div>
                            <div>
                                <label>password</label>
                                <input name="password" type="password" className="form-control" onChange={this.handleChange} />
                            </div>
                            <div className="container-valid text-center">
                                <input type="submit" value="Valider" className="btn btn-primary" onChange={this.handleChange} />
                            </div>
                        </form>
                    </div>
                    <div><Link to="/">Retour à l'accueil</Link></div>
                </div>
            </div>
        )
    }
}
