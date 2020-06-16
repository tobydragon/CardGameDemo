import React from "react";
import {getFromServer, postToServer} from "./Comm";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Container from "react-bootstrap/Container";

import Hand from "./Hand";

export default class PlayerHand extends React.Component {
    constructor(props) {
        super(props);
        this.handleHit = this.handleHit.bind(this);
        this.handleDeal = this.handleDeal.bind(this);
        this.handleRoundStateResponse = this.handleRoundStateResponse.bind(this);
        this.state={
            playerCards: [
                { suit: "spades", value: 2 },
                { suit: "hearts", value: 4 },
                { suit: "diamonds", value: 6 },
                { suit: "hearts", value: 5 }
            ]
        };
    }

    handleRoundStateResponse(responseJson){
        this.setState({
            playerCards: responseJson.playerHand.cards
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
                        <Hand ownerName={this.state.playerId} cards={this.state.playerCards}/>
                    </Col>
                </Row>
            </Container>
        );
    }
}