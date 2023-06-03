import React from 'react'
import logo from './logo.svg';
import {Link, useNavigate} from "react-router-dom";
import './login.scss';
import axios from "axios";
import {AUTH_TOKEN_KEY} from "./App";

export function Login({setUserConnectedInfoLogin}) {
    const history = useNavigate();
    const onSubmit = ((e) => {
            e.preventDefault();
            const form = new FormData(e.target);
            axios.post("/authenticate", {
                email: form.get("email"),
                password: form.get("password")
            }).then(response => {
                const bearerToken = response?.headers?.authorization;
                if (bearerToken && bearerToken.slice(0, 7) === 'Bearer ') {
                    const jwt = bearerToken.slice(7, bearerToken.length);
                    sessionStorage.setItem(AUTH_TOKEN_KEY, jwt)
                    // redirection
                    history("/home");
                    setUserConnectedInfoLogin(form.get("email"));
                }
            }).catch(() => {
                //To catch
            })

        }
    )

    return (
        <div className="login-container">
            <div>
                <div>
                    <img className="logo-img-m" src={logo} alt="Logo"/>
                </div>
                <div className="title">
                    Bienvenue sur Tribean!
                </div>
                <div className="form-container">
                    <form onSubmit={onSubmit}>
                        <span>Mail: </span>
                        <input type="text" className="form-control" name="email"></input>
                        <span>Passsword: </span>
                        <input type="password" className="form-control" name="password"></input>
                        <div>
                            <input type="submit" className="btn btn-primary" value="OK"/>
                        </div>
                    </form>
                </div>
                <div><Link to="/addUser">M'inscrire</Link></div>
            </div>
        </div>
    )
}

// Wrap and export
export default function Wrapper(props) {
    const history = useNavigate();
    return <Login {...props} history={history} />;
}