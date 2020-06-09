import React from "react";
import ReactDOM from "react-dom";
import BlackJackGame from "./BlackJack";


const reactElement = document.getElementById("react");
ReactDOM.render(
    <React.StrictMode>
        <BlackJackGame/>
    </React.StrictMode>,
    reactElement
);