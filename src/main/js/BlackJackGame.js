import React from "react";

import Jumbotron from "react-bootstrap/Jumbotron";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

import Button from "react-bootstrap/Button";
import ButtonToolbar from "react-bootstrap/ButtonToolbar";
import CardGroup from "react-bootstrap/CardGroup";
import Card from "react-bootstrap/Card";

import {getFromServer, postToServer} from "./Comm";

//TODO: figure out how to make this work in webpack
// import "./BlackJack.css";

function CardsListViewFromCardListModel(props) {
    const cardsArray = props.cards.map(aCardModel => (
        <Card>
            <Card.Title>{aCardModel.value}</Card.Title>
            <Card.Title> of</Card.Title>
            <Card.Title>{aCardModel.suit}</Card.Title>
        </Card>
    ));
    return <CardGroup className="cardDeck">{cardsArray}</CardGroup>;
}

class HandButtons extends React.Component {
    constructor(props) {
        super(props);
        this.handleHitClick = this.handleHitClick.bind(this);
    }

    handleHitClick(e) {
        this.props.onHitClick();
    }

    render() {
        return (
            <ButtonToolbar>
                <Button onClick={this.handleHitClick}>Hit </Button>
                <Button>Stand</Button>
            </ButtonToolbar>
        );
    }
}

class Hand extends React.Component {
    constructor(props) {
        super(props);
        this.handleHit = this.handleHit.bind(this);
        this.handleHandResponse = this.handleHandResponse.bind(this);
        this.state = {
            cards: [ ],
            gameId: props.gameId,
            apiUrl: props.baseApiUrl+"/"+props.gameId
        };
    }

    componentDidMount() {
        getFromServer(this.state.apiUrl, "", this.handleHandResponse);
    }

    handleHandResponse(responseJson){
        this.setState({ cards: responseJson.hand.cards })
    }

    handleHit() {
        postToServer(this.state.apiUrl, "/hit", "", this.handleHandResponse);
    }

    render() {
        return (
            <Container>
                <Row>
                    <Col>
                        <CardsListViewFromCardListModel cards={this.state.cards} />
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <HandButtons onHitClick={this.handleHit} />
                    </Col>
                </Row>
            </Container>
        );
    }
}

export default function BlackJackGame(props) {
    return (
        <Container>
            <Jumbotron>
                <h1 className="center">Welcome To Blackjack</h1>
            </Jumbotron>
            <Hand gameId={props.gameId} baseApiUrl={props.baseApiUrl}/>
        </Container>
    );
}