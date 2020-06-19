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
                    <br/>
                    <HandButtons gameState={props.gameState}
                                 onHitClick={props.handleHit}
                                 onDealClick={props.handleDeal}
                                 onStandClick={props.handleStand}
                    />
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
        this.handleStandClick= this.handleStandClick.bind(this);

        this.state = {
            onHitClick: props.onHitClick,
            onDealClick: props.onDealClick,
            onStandClick: props.onStandClick,
        }

    }

    handleHitClick(e) {
        this.state.onHitClick();
    }

    handleDealClick(e) {
        this.state.onDealClick();
    }

    handleStandClick(e){
        this.state.onStandClick();
    }

    render() {
        if (this.props.gameState === "PLAYING"){
            console.log("PlayerHand.render: PLAYING");
            return (
                <ButtonToolbar>
                    <Button disabled={true} onClick={this.handleDealClick}> Deal</Button>
                    <Button onClick={this.handleHitClick}>Hit </Button>
                    <Button onClick={this.handleStandClick}>Stand</Button>
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

