import React from 'react'
import logo from './logo.svg';
import { Link } from "react-router-dom";
import './login.scss';

export class Login extends React.Component {

    constructor() {
        super();
        this.state = { userData: {} }
        //sans les this.truc = this.truc.bind(this), on a des objets null
        this.onSubmit = this.onSubmit.bind(this)
        this.handleChange = this.handleChange = this.handleChange.bind(this)
    }

    onSubmit = (event) => {
        event.preventDefault();
        console.log("onsubmit")
        console.log(this.state.userData)
    }

    handleChange = (event) => {
        let currentState = {...this.state.userData};
        currentState[event.target.name] = event.target.value;
        this.setState({ userData: {currentState} })
    }

    render() {
        return (
            <div className="login-container">
                <div>
                    <div>
                        <img className="logo-img-m" src={logo} alt="Logo" />
                    </div>
                    <div className="title">
                        Bienvenue sur Tribean!
                    </div>
                    <div className="form-container">
                        <form onSubmit={this.onSubmit}>
                            <span>Mail: </span>
                            <input type="text" className="form-control" name="email" onChange={this.handleChange}></input>
                            <span>Passsword: </span>
                            <input type="password" className="form-control" name="password" onChange={this.handleChange}></input>
                            <div>
                                <input type="submit" className="btn btn-primary" value="OK" />
                            </div>
                        </form>
                    </div>
                    <div><Link to="/addUser">M'inscrire</Link></div>
                </div>
            </div>
        )
    }
}