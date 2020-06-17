import React from "react";
import {getFromServer, postToServer} from "./Comm";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Container from "react-bootstrap/Container";

import Hand from "./Hand"
import PlayerHand from "./PlayerHand";

export default class Round extends React.Component {
    constructor(props) {
        super(props);
        this.handleHit = this.handleHit.bind(this);
        this.handleDeal = this.handleDeal.bind(this);
        this.handleRoundStateResponse = this.handleRoundStateResponse.bind(this);
        this.state = {
            gameId: props.gameId,
            apiUrl: props.baseApiUrl+"/"+props.gameId,
            playerId: props.playerId,
            playerCards: [],
            dealerCards: [],

        };
    }

    componentDidMount() {
        getFromServer(this.state.apiUrl,"")
            .then(jsonResponse => this.handleRoundStateResponse(jsonResponse))
            .catch(errorResponse=>console.error("In Round.componentDidMount: no state loaded, \n" + errorResponse));
    }

    handleRoundStateResponse(responseJson){
        this.setState({
            playerCards: responseJson.playerHand.cards,
            dealerCards: responseJson.dealerHand.cards
        })
    }

     handleHit() {
         postToServer(this.state.apiUrl, "/hit", "", this.handleRoundStateResponse);
     }

     handleDeal() {
         postToServer(this.state.apiUrl, "/deal", "", this.handleRoundStateResponse)
     }

     render(){
        return (
            <Container>
                <Row>
                    <Col>
                        <Hand ownerName="Dealer" cards={this.state.dealerCards}/>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <PlayerHand ownerName={this.state.playerId}
                                    cards={this.state.playerCards}
                                    handleHit={this.handleHit}
                                    handleDeal={this.handleDeal}
                        />
                    </Col>
                </Row>
            </Container>
        );
     }
 }