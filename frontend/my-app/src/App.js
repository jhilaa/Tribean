import logo from './logo.svg';
import React from 'react';
import { Routes, Route } from 'react-router';
import { ListResources } from './ListResources';
import { AddResource  } from './AddResource';
import { AddUser } from './AddUser';
import { Login } from './Login';
import { Home } from './Home';
import { Header } from './Header';
import { useEffect } from 'react'
import 'bootstrap/dist/css/bootstrap.min.css'
import axios from 'axios'
import {SideBar} from "./SideBar";

export const AUTH_TOKEN_KEY = 'jhi-authenticationToken'

function App() {
    const[userInfo, setUserInfo] = React.useState('');

    useEffect(() => {
        // intercepteur sur chaque requête
        axios.interceptors.request.use(function (request) {
            const token = sessionStorage.getItem(AUTH_TOKEN_KEY)
            if (token) {
                request.headers.Authorization = `Bearer ${token}`;
            }
            return request
        }, (error) => {
            return Promise.reject(error);
        });
    })

  return (
  <div>
      <div className="App">
          <Header userInfo={userInfo}/>
          <Routes>
              <Route path="home" element ={<Home userInfo={userInfo}/>} />
              <Route path="listResources" element={<ListResources />} />
              <Route path="addResource" element={<AddResource />} />
              <Route path="addResource/:resourceId" element={<AddResource />} />
              <Route path="login" element={<Login />} />
              <Route path="addUser" element={<AddUser setUserInfo={setUserInfo}/>} />
          </Routes>
      </div>
  </div>
  );
}

export default App;
