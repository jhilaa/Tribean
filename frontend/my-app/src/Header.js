import {Link, useNavigate} from "react-router-dom";
import logo from './logo.svg';
import 'bootstrap/dist/css/bootstrap.min.css'
import './header.scss'
import React from "react";
import {AUTH_TOKEN_KEY} from "./App";

export function Header({userInfo}) {
    const history = useNavigate();

    /* le token est dans le storage, il "suffit" de le supprimer */
    const signout = () => {
        sessionStorage.removeItem(AUTH_TOKEN_KEY);
        history('/login');
    }

    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <div className="container-fluid">

                <button type="button" id="sidebarCollapse" className="btn btn-info">
                    <i className="bi bi-layout-sidebar-inset"></i>
                </button>
                <button className="btn btn-dark d-inline-block d-lg-none ml-auto" type="button"
                        data-toggle="collapse" data-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false"
                        aria-label="Toggle navigation">
                    <i className="fas fa-align-justify"></i>
                </button>

                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="nav navbar-nav ml-auto">
                        <li className="nav-item active">
                            <a className="nav-link" href="#">Contact</a>
                        </li>
                        <li className="nav-item active nav-link">
                            <Link to="/login">Login</Link>
                        </li>
                    </ul>
                 </div>
            </div>
        </nav>
    )
}