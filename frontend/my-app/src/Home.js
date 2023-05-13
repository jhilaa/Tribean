import React, {useState, useEffect} from "react";
import {ListResources} from "./ListResources";
import axios from "axios";
import Spinner from "react-bootstrap/Spinner";
import {Link} from "react-router-dom";
import "./style.css";
import {SideBar} from "./SideBar";
import {Header} from "./Header";

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
            <SideBar />

            {/********* page content ******/}
            <div id="content">
                <Header />

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