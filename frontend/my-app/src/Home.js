import React, {useState, useEffect} from "react";
import {ListResources} from "./ListResources";
import axios from "axios";
import Spinner from "react-bootstrap/Spinner";
import {Link} from "react-router-dom";
import "./style.css";
import {SideBar} from "./SideBar";

export function Home() {
    const [listResources, setListResources] = React.useState([
        {id: 10, title: "sql", description: "datawarehouse", tags: []}])
    const [showSpinner, setShowSpinner] = useState(false);

    React.useEffect(() => {
        axios.get("/resources/all").then(response => {
            setListResources(response.data)
        }).then(() => {
            setShowSpinner(!showSpinner)
        });
    }, []);


    return (
        <div className="wrapper">
            {/********* Sidebar ******/}
            <div>
            <SideBar />
            </div>

            {/********* page content ******/}
            <div id="content">
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
                                <li className="nav-item active">
                                    <a className="nav-link" href="#">Login</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>

                <div>
                    <Spinner animation="border" role="status" hidden={showSpinner}>
                        <span className="visually-hidden">Loading...</span>
                    </Spinner>
                </div>
                <ListResources listResources={listResources}/>
            </div>
        </div>


    );
}