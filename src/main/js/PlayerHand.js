import React from "react";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Container from "react-bootstrap/Container";
import Button from "react-bootstrap/Button";
import ButtonToolbar from "react-bootstrap/ButtonToolbar";
import Hand from "./Hand";
import Jumbotron from "react-bootstrap/Jumbotron";

export default function PlayerHand(props) {
    return (
        <Container>
            <Row>
                <Col>
                    <Hand ownerName={props.ownerName} cards={props.cards}/>
                    <HandButtons gameState={props.gameState} onHitClick={props.handleHit} onDealClick={props.handleDeal}/>
                    <WinLossDisplay gameState={props.gameState}/>
                </Col>
            </Row>
        </Container>
    );
}

class HandButtons extends React.Component {
    constructor(props) {
        super(props);

        this.handleHitClick = this.handleHitClick.bind(this);
        this.handleDealClick = this.handleDealClick.bind(this);
        this.state = {
            onHitClick: props.onHitClick,
            onDealClick: props.onDealClick,
        }

    }

    handleHitClick(e) {
        this.state.onHitClick();
    }

    handleDealClick(e) {
        this.state.onDealClick();
    }

    render() {
        if (this.props.gameState === "PLAYING"){
            console.log("PlayerHand.render: PLAYING");
            return (
                <ButtonToolbar>
                    <Button disabled={true} onClick={this.handleDealClick}> Deal</Button>
                    <Button onClick={this.handleHitClick}>Hit </Button>
                    <Button>Stand</Button>
                </ButtonToolbar>
            );
        }
        else {
            return (
                <ButtonToolbar>
                    <Button onClick={this.handleDealClick}> Deal</Button>
                    <Button disabled={true} onClick={this.handleHitClick}>Hit </Button>
                    <Button disabled={true}> Stand</Button>
                </ButtonToolbar>
            );
        }
    }
}

function WinLossDisplay(props){
    if (props.gameState!== null && props.gameState !== "PLAYING"){
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