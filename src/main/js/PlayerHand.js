import React from "react";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Container from "react-bootstrap/Container";
import Button from "react-bootstrap/Button";
import ButtonToolbar from "react-bootstrap/ButtonToolbar";
import Form from "react-bootstrap/Form";
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

        this.handleBetChange = this.handleBetChange.bind(this);
        this.handleHitClick = this.handleHitClick.bind(this);
        this.handleDealClick = this.handleDealClick.bind(this);
        this.handleStandClick= this.handleStandClick.bind(this);

        this.state = {
            onHitClick: props.onHitClick,
            onDealClick: props.onDealClick,
            onStandClick: props.onStandClick,
            currBet: 0.0,
        }

    }

    handleHitClick(e) {
        this.state.onHitClick();
    }

    handleDealClick(e) {
        this.state.onDealClick(this.state.currBet);
    }

    handleStandClick(e){
        this.state.onStandClick();
    }

    handleBetChange(event){
        this.setState({
            currBet: event.target.value,
        });
    }


    render() {
        if (this.props.gameState === "PLAYING"){
            console.log("PlayerHand.render: PLAYING");
            return (
                <div>
                    <ButtonToolbar>
                        <Button disabled={true} onClick={this.handleDealClick}> Deal</Button>
                        <Button onClick={this.handleHitClick}>Hit </Button>
                        <Button onClick={this.handleStandClick}>Stand</Button>
                    </ButtonToolbar>
                </div>
            );
        }
        else {
            return (
                <div>
                    <ButtonToolbar>
                        <Button onClick={this.handleDealClick}> Deal</Button>
                        <Button disabled={true} onClick={this.handleHitClick}>Hit </Button>
                        <Button disabled={true}> Stand</Button>
                    </ButtonToolbar>
                    <Form>
                        <Form.Label>Place your bets in the box below</Form.Label>
                        <Form.Control onChange={this.handleBetChange} name="currBet" type="double" placeholder="Place bet here" />
                    </Form>
                </div>
            );
        }
    }
}

