import {Link} from "react-router-dom";
import logo from './logo.svg';
import 'bootstrap/dist/css/bootstrap.min.css'
import './header.scss'

export function Header() {
    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <img className="logo-img-s" src={logo}/>
            <button className="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>

            <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav mr-auto">
                    <li className="nav-item">
                        <Link className="nav-link" to="/ListResources">Ressources disponibles</Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link" to="/AddResource">Nouvelle ressource</Link>
                    </li>
                </ul>
                <div>Bienvenue, ...</div>
                <button variant="secondary">Se déconnecter</button>
            </div>
        </nav>
    )
}