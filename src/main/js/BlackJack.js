import React from "react";

import Jumbotron from "react-bootstrap/Jumbotron";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

import Button from "react-bootstrap/Button";
import ButtonToolbar from "react-bootstrap/ButtonToolbar";
import CardGroup from "react-bootstrap/CardGroup";
import Card from "react-bootstrap/Card";

//TODO: figure out how to make this work in webpack
// import "./BlackJack.css";

function CardsListComponent(cardListModel) {
    const cardsArray = cardListModel.map(aCardModel => (
        <Card>
            <Card.Title>{aCardModel.value}</Card.Title>
            <Card.Title> of</Card.Title>
            <Card.Title>{aCardModel.suit}</Card.Title>
        </Card>
    ));
    return <CardGroup className="cardDeck">{cardsArray}</CardGroup>;
}

function HandButtons() {
    return (
        <ButtonToolbar>
            <Button>Hit</Button>
            <Button>Stand</Button>
        </ButtonToolbar>
    );
}

function Hand() {
    const exampleHandModel = {
        cards: [
            { suit: "spades", value: 2 },
            { suit: "hearts", value: 4 },
            { suit: "diamonds", value: 6 },
            { suit: "hearts", value: 5 }
        ]
    };
    return (
        <Container>
            <Row>
                <Col>{CardsListComponent(exampleHandModel.cards)}</Col>
            </Row>
            <Row>
                <Col>{HandButtons()}</Col>
            </Row>
        </Container>
    );
}

export default function BlackJackGame() {
    return (
        <Container>
            <Jumbotron>
                <h1 className="center">Welcome To Blackjack</h1>
            </Jumbotron>
            <div>{Hand()}</div>
        </Container>
    );
}
