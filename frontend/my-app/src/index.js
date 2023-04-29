import React from 'react';
import ReactDOM, {createRoot} from 'react-dom/client';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter } from "react-router-dom";

const container = document.getElementById('root')
const root = createRoot(container)

root.render(
    //<React.StrictMode>
        <BrowserRouter>
            <App />
        </BrowserRouter>
    //</React.StrictMode>
);

