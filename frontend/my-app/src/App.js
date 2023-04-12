import logo from './logo.svg';
import {Routes, Route} from 'react-router';
import {ListResources} from './ListResources';
import {MyResources} from './MyResources';
import {AddResource} from './AddResource';
import {AddUser} from './AddUser';
import {Login} from './Login';

function App() {
  return (
      <div className="App">
          <Routes>
              <Route path="listResources" element={<ListResources />} />
              <Route path="myResources" element={<MyResources />} />
              <Route path="addResource" element={<AddResource />} />
              <Route path="addResource/:bookId" element={<AddResource />} />
              <Route path="myResources" element={<MyResources />} />
              <Route path="login" element={<Login />} />
              <Route path="addUser" element={<AddUser />} />
          </Routes>
      </div>
  );
}

export default App;
