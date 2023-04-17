import logo from './logo.svg';
import { Routes, Route } from 'react-router';
import { ListResources } from './ListResources';
import { AddResource  } from './AddResource';
import { AddUser } from './AddUser';
import { Login } from './Login';
import { Header } from './Header';
import { useEffect } from 'react'
import 'bootstrap/dist/css/bootstrap.min.css'
import axios from 'axios'

export const AUTH_TOKEN_KEY = 'jhi-authenticationToken'

function App() {

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
      <Header />
      <div className="App">
          <Routes>
              <Route path="listResources" element={<ListResources />} />
              <Route path="addResource" element={<AddResource />} />
              <Route path="addResource/:resourceId" element={<AddResource />} />
              <Route path="login" element={<Login />} />
              <Route path="addUser" element={<AddUser />} />
          </Routes>
      </div>
  </div>
  );
}

export default App;
