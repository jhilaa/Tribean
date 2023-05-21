import React from 'react'
import {Link, useNavigate} from "react-router-dom";
import {useState, useEffect} from 'react'
import './AddUser.scss'
import axios from "axios";
import { AUTH_TOKEN_KEY } from "./App";

export function AddUser ({setUserConnectedInfoLogin}) {
    const history = useNavigate();

    const onSubmit = ((e) => {
        e.preventDefault();
        const form = new FormData(e.target);
        axios.post('/user', {
            "userInfoId": 0,
            "lastname": form.get("lastname"),
            "firstname": form.get("firstname"),
            "email": form.get("email"),
            "password": form.get("password")
        }).then(response => {
            const bearerToken = response?.headers?.authorization;
            if (bearerToken && bearerToken.slice(0, 7) === 'Bearer ') {
                const jwt = bearerToken.slice(7, bearerToken.length);
                sessionStorage.setItem(AUTH_TOKEN_KEY, jwt)
            }
            setUserConnectedInfoLogin(form.get("email"));
            // redirection
            history("/home");
        })
    })

    const handleChange = (e) => {
    }

        return (
            <div className="add-user-container">
                <div>
                    <h1>M'inscrire</h1>
                    <div>
                        <form onSubmit={onSubmit}>
                            <div>
                                <label>email</label>
                                <input name="email" type="text" className="form-control" onChange={handleChange}/>
                            </div>
                            <div>
                                <label>nom</label>
                                <input name="lastname" type="text" className="form-control"
                                       onChange={handleChange}/>
                            </div>
                            <div>
                                <label>prenom</label>
                                <input name="firstname" type="text" className="form-control"
                                       onChange={handleChange}/>
                            </div>
                            <div>
                                <label>password</label>
                                <input name="password" type="password" className="form-control"
                                       onChange={handleChange}/>
                            </div>
                            <div className="container-valid text-center">
                                <input type="submit" value="Valider" className="btn btn-primary"
                                       onChange={handleChange}/>
                            </div>
                        </form>
                    </div>
                    <div><Link to="home">Retour à l'accueil</Link></div>
                </div>
            </div>
        )
}
