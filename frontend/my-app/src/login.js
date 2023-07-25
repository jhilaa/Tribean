import axios from "axios";
import {AUTHORIZATION_HEADER} from "./App";



function doLogin(email, password) {
    axios.post("/authenticate", {
        email: email,
        password: password
    }).then(response => {
        const bearerToken = response?.headers?.authorization;
        if (bearerToken && bearerToken.slice(0, 7) === 'Bearer ') {
            const jwt = bearerToken.slice(7, bearerToken.length);
            sessionStorage.setItem(AUTHORIZATION_HEADER, jwt)
        }
    })
}


export function login(email, pwd) {
    return new Promise((email, pwd) => {doLogin(email, pwd); return null})
};