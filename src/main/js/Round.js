import React from "react";
import {getFromServer, postToServer} from "./Comm";

class round extends React.Component {
    constructor(props) {
        super(props);
        this.handleHit = this.handleHit.bind(this);
        this.handleDeal = this.handleDeal.bind(this);
        this.handleHandResponse = this.handleHandResponse.bind(this);
        this.state = {
            gameId: props.gameId,
            apiUrl: props.baseApiUrl+"/"+props.gameId
        };
    }

    componentDidMount() {
        getFromServer(this.state.apiUrl,"",this.handleHandResponse);
    }

    handlePlayerHandResponse(responseJson){
        this.setState({cards: responseJson.playerHand.cards})
    }

    handleDealerHandResponse(responseJson){
        this.setState({cards: responseJson.dealerHand.cards})
    }

     handleHit() {
         postToServer(this.state.apiUrl, "/hit", "", this.handlePlayerHandResponse);
     }

     handleDeal() {
         postToServer(this.state.apiUrl, "/deal", "", this.handleHandResponse)
     }
 }