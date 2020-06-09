import React from "react";
import ReactDOM from "react-dom";
import BlackJack from "./BlackJack";


const reactElement = document.getElementById("react");
ReactDOM.render(
    <React.StrictMode>
        <BlackJack/>
    </React.StrictMode>,
    reactElement
);