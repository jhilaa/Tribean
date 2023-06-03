import React, {useState, useEffect} from "react";
import {ListResources} from "./ListResources";
import axios from "axios";
import Spinner from "react-bootstrap/Spinner";
import {Link, useNavigate} from "react-router-dom";
import "./style.css";
import {SideBar} from "./SideBar";

export function Home() {
    const [listResources, setListResources] = useState([])
    const [showModal, setShowModal] = React.useState(false)
    const [loading, setLoading] = useState(false)
    const history = useNavigate();

    React.useEffect(() => {
        axios.get("/resources/all").then(response => {
            setListResources(response.data)
        })
    }, []);

    return (
        <div className="wrapper">
            <div id="content" className="d-flex flex-row p-0" >
            <SideBar />
            {/********* page content ******/}
            <ListResources listResources={listResources}/>
            </div>
        </div>


    );
}