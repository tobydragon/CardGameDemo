import React from "react";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Container from "react-bootstrap/Container";
import Button from "react-bootstrap/Button";
import ButtonToolbar from "react-bootstrap/ButtonToolbar";
import Hand from "./Hand";

export default function PlayerHand(props) {
    return (
        <Container>
            <Row>
                <Col>
                    <Hand ownerName={props.ownerName} cards={props.cards}/>
                    <br/>
                    <HandButtons onHitClick={props.handleHit} onDealClick={props.handleDeal}/>
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
        return (
            <ButtonToolbar>
                <Button onClick={this.handleDealClick}> Deal</Button>
                <Button onClick={this.handleHitClick}>Hit </Button>
                <Button>Stand</Button>
            </ButtonToolbar>
        );
    }
}