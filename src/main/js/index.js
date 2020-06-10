import React from "react";
import ReactDOM from "react-dom";
import BlackJackApp from "./BlackJackApp";


const reactElement = document.getElementById("react");
ReactDOM.render(
    <React.StrictMode>
        <BlackJackApp/>
    </React.StrictMode>,
    reactElement
);