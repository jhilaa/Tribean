import logo from './logo.svg';
import {Routes, Route} from 'react-router';
import {ListResources} from './ListResources';
import {MyResources} from './MyResources';
import {AddResource} from './AddResource';
import {AddUser} from './AddUser';
import {Login} from './Login';
import {Header} from './Header';

import 'bootstrap/dist/css/bootstrap.min.css'

function App() {
  return (
  <div>
      <Header />
      <div className="App">
          <Routes>
              <Route path="listResources" element={<ListResources />} />
              <Route path="myResources" element={<MyResources />} />
              <Route path="addResource" element={<AddResource />} />
              <Route path="addResource/:resourceId" element={<AddResource />} />
              <Route path="myResources" element={<MyResources />} />
              <Route path="login" element={<Login />} />
              <Route path="addUser" element={<AddUser />} />
          </Routes>
      </div>
  </div>
  );
}

export default App;
