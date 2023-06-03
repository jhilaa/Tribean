import logo from './logo.svg';
import React, {useState} from 'react';
import { Routes, Route } from 'react-router';
import { ListResources } from './ListResources';
import { AddResource  } from './AddResource';
import { AddUser } from './AddUser';
import { Login } from './Login';
import { Home } from './Home';
import { Header } from './Header';
import { useEffect } from 'react'
import Spinner from 'react-bootstrap/Spinner'
import 'bootstrap/dist/css/bootstrap.min.css'
import axios from 'axios'
import {SideBar} from "./SideBar";
import {useNavigate} from "react-router-dom";

export const AUTH_TOKEN_KEY = 'jhi-authenticationToken'

const UserConnected = ({ setUserConnectedInfoLogin, userConnectedInfoLogin }) => {
    const history = useNavigate();
    React.useEffect(() => {
        setUserConnectedInfoLogin(null)
        axios.get('/isConnected').then(response => {
            setUserConnectedInfoLogin(response.data)
        })
    }, [history, setUserConnectedInfoLogin])

    return (<>
        {userConnectedInfoLogin && <Header userConnectedInfoLogin={userConnectedInfoLogin} setUserConnectedInfoLogin={setUserConnectedInfoLogin} />}
    </>)
}

function App() {
    const [userConnectedInfoLogin, setUserConnectedInfoLogin] = useState('');
    const [loading, setLoading] = useState(false)

    useEffect(() => {
        // intercepteur sur chaque requête
        axios.interceptors.request.use(function (request) {
          const token = sessionStorage.getItem(AUTH_TOKEN_KEY)
            if (token) {
                request.headers.Authorization = `Bearer ${token}`;
            }
            setLoading(true)
            return request
        }, (error) => {
            setLoading(false)
            return Promise.reject(error);
        });

        axios.interceptors.response.use(function (response) {
            setLoading(false)
            return response;
        }, (error) => {
            setLoading(false)
            return Promise.reject(error);
        });
    })

  return (
    <div id="page">
      <Header userConnectedInfoLogin={userConnectedInfoLogin} setUserConnectedInfoLogin={setUserConnectedInfoLogin}/>
        <div className="App">
          <Routes>
              <Route path="/" element={<Login setUserConnectedInfoLogin={setUserConnectedInfoLogin}/>} />
              <Route path="login" element={<Login setUserConnectedInfoLogin={setUserConnectedInfoLogin}/>} />
              <Route path="home" element ={<Home />} />
              <Route path="addResource" element={<AddResource />} />
              <Route path="addResource/:resourceId" element={<AddResource />} />
              <Route path="addUser" element={<AddUser setUserConnectedInfoLogin={setUserConnectedInfoLogin}/>} />
              <Route path="*" element={<Login setUserConnectedInfoLogin={setUserConnectedInfoLogin}/>} />
          </Routes>
      </div>
  </div>
  );

}

export default App;
