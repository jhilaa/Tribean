import {Link} from "react-router-dom"

export function Header() {
    return <div>
        <Link to="listResources">Resources</Link>
        <Link to="MyResources">Mes resources</Link>
    </div>
}