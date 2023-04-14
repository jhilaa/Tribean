import {Link} from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css'

export function Header() {
    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
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
                        <Link className="nav-link" to="/AddResources">Nouvelle ressource</Link>
                    </li>
                </ul>
                <div>Bienvenue, ...</div>
                <button variant="secondary">Se déconnecter</button>
            </div>
        </nav>
    )
}