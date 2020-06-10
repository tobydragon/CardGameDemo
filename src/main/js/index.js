import React from "react";
import ReactDOM from "react-dom";
import App from "./BlackJackApp";


const reactElement = document.getElementById("react");
ReactDOM.render(
    <React.StrictMode>
        <App/>
    </React.StrictMode>,
    reactElement
);