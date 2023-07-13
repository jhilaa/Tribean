import React, {useState, useEffect} from "react";
import logo from './logo.svg';
import {Link, useNavigate} from "react-router-dom";
import './login.scss';
import axios from "axios";
import {AUTHORIZATION_HEADER} from "./App";
import SimpleModal from './SimpleModal';
//import {useState} from "@types/react";

export function Login({setUserConnectedInfoLogin}) {
    const [showModal, setShowModal] = useState(false);
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
                    sessionStorage.setItem(AUTHORIZATION_HEADER, jwt)
                    // redirection
                    history("/home");
                    setUserConnectedInfoLogin(form.get("email"));
                }
            }).catch(() => {
                setShowModal(true);
            })

        }
    )

    const title = "Login incorrect"
    const bodyTxt = "Login ou mot de passe incorrect"
    return (
        <>
            <div className="login-container">
                <div>
                    <div>
                        <img className="logo-img-m" src={logo} alt="Logo"/>
                    </div>
                    <div className="title">
                        Bienvenue sur Tribean.
                    </div>
                    <div className="form-container">
                        <form onSubmit={onSubmit}>
                            <span>Mail: </span>
                            <input type="text" className="form-control" name="email"></input>
                            <span>Password: </span>
                            <input type="password" className="form-control" name="password"></input>
                            <div>
                                <input type="submit" className="btn btn-primary" value="OK"/>
                            </div>
                        </form>
                    </div>
                    <div className="text-center"><Link to="/addUser">M'inscrire</Link></div>
                </div>
            </div>

            <SimpleModal title={title}
                         bodyTxt={bodyTxt}
                         handleCloseModal={() => {setShowModal(false)}}
                         //showModal={showModal} />
                         showModal={showModal} />
        </>
    )
}

// Wrap and export
export default function Wrapper(props) {
    const history = useNavigate();
    return <Login {...props} history={history}/>;
}