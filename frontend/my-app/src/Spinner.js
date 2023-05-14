import Spinner from "react-bootstrap/Spinner";
import React from "@types/react";

export function Spinner() {
    return (
        <div>
            <Spinner animation="border" role="status" hidden={showSpinner}>
                <span className="visually-hidden">Loading...</span>
            </Spinner>
        </div>
    )
}