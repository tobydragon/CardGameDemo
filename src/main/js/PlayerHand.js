import React from "react";
import {getFromServer, postToServer} from "./Comm";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Container from "react-bootstrap/Container";
import Button from "react-bootstrap/Button";
import ButtonToolbar from "react-bootstrap/ButtonToolbar";

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
                        <HandButtons/>
                    </Col>
                </Row>
            </Container>
        );
    }
}

class HandButtons extends React.Component {
    constructor(props) {
        super(props);
        this.handleHitClick = this.handleHitClick.bind(this);
        this.handleDealClick = this.handleDealClick.bind(this);
    }

    handleHitClick(e) {
        this.props.onHitClick();
    }

    handleDealClick(e) {
        this.props.onDealClick();
    }

    render() {
        return (
            <ButtonToolbar>
                <Button onClick={this.handleDealClick}> Deal</Button>
                <Button onClick={this.handleHitClick}>Hit </Button>
                <Button>Stand</Button>
            </ButtonToolbar>
        );
    }
}