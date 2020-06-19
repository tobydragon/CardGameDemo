import React from "react";
import {getFromServer, postToServer, putToServer} from "./Comm";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Container from "react-bootstrap/Container";
import Table from "react-bootstrap/Table";

import Hand from "./Hand"
import PlayerHand from "./PlayerHand";
import Jumbotron from "react-bootstrap/Jumbotron";


export default class Round extends React.Component {
    constructor(props) {
        super(props);
        // this.RoundStates= {
        //     BETTING: 1,
        //     PLAYING: 2,
        //     WON_BLACKJACK: 10,
        //     WON_DEALER_BUST: 11,
        //     WON_BEAT_DEALER: 12,
        //     LOST_PLAYER_BUST: 20,
        //     LOST_DEALER_BEATS_PLAYER: 21,
        //     PUSH: 22,
        // }
        this.handleHit = this.handleHit.bind(this);
        this.handleDeal = this.handleDeal.bind(this);
        this.handleStand = this.handleStand.bind(this);
        this.handleRoundStateResponse = this.handleRoundStateResponse.bind(this);
        this.state = {
            gameId: props.gameId,
            apiUrl: props.baseApiUrl+"/"+props.gameId,
            playerId: "player",
            playerCards: [],
            dealerCards: [],
            gameState: "BETTING",
        };
    }

    componentDidMount() {
        getFromServer(this.state.apiUrl,"")
            .then(jsonResponse => this.handleRoundStateResponse(jsonResponse))
            .catch(errorResponse=>console.error("ERROR: In Round.componentDidMount: no state loaded, \n" + errorResponse));
    }

    handleRoundStateResponse(responseJson){
        console.log("INFO:\t Round.handleRoundStateResponse, got:");
        console.log(responseJson);
        this.setState({
            playerId: responseJson.user.id,
            playerCards: responseJson.playerHand.cards,
            dealerCards: responseJson.dealerHand.cards,
            gameState: responseJson.state
        })
    }

     handleHit() {
         postToServer(this.state.apiUrl, "/hit", "", this.handleRoundStateResponse);
     }

     handleDeal() {
         postToServer(this.state.apiUrl, "/deal", "", this.handleRoundStateResponse);
     }

     handleStand(){
         putToServer(this.state.apiUrl, "/stay", "", this.handleRoundStateResponse);
     }

     render(){
        return (
            <div>
                <Row>
                    <Col>
                        <Hand ownerName="Dealer" cards={this.state.dealerCards}/>
                    </Col>
                </Row>
                <br/>
                <Row>
                    <Col>
                        <PlayerHand ownerName={this.state.playerId}
                                    cards={this.state.playerCards}
                                    gameState={this.state.gameState}
                                    handleHit={this.handleHit}
                                    handleDeal={this.handleDeal}
                                    handleStand={this.handleStand}
                        />
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <WinLossDisplay gameState={this.state.gameState} />
                    </Col>
                </Row>
                <div className="idTable">
                    <Table striped bordered hover variant="dark">
                        <thead>
                            <tr>
                                <th>Game ID:</th>
                                <th>User ID:</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th>{this.state.gameId}</th>
                                <th>{this.state.playerId}</th>
                            </tr>
                        </tbody>
                    </Table>
                </div>
            </div>
        );
     }
 }

function WinLossDisplay(props){
    if (props.gameState!== null && props.gameState !== "PLAYING" && props.gameState !== "BETTING"){
        return (
            <Jumbotron>
                <h1 className="center">{props.gameState} </h1>
            </Jumbotron>
        );
    }
    else{
        return ("");
    }
}