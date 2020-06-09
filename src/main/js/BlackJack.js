import React from "react";

import Jumbotron from "react-bootstrap/Jumbotron";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

import Button from "react-bootstrap/Button";
import ButtonToolbar from "react-bootstrap/ButtonToolbar";
import CardGroup from "react-bootstrap/CardGroup";
import Card from "react-bootstrap/Card";

//TODO: fix import of css
// import "./BlackJack.css";

function Cards(cardListModel) {
    return (
        <CardGroup className="cardDeck">
            <Card>
                <Card.Title>King</Card.Title>
                <Card.Title>Hearts</Card.Title>
            </Card>
            <Card>
                <Card.Title>9</Card.Title>
                <Card.Title>Spades</Card.Title>
            </Card>
        </CardGroup>
    );
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
        cards: [{ suit: "spades", value: 10 }, { suit: "hearts", value: 4 }]
    };
    return (
        <Container>
            <Row>
                <Col>{Cards(exampleHandModel.cards)}</Col>
            </Row>
            <Row>
                <Col>{HandButtons()}</Col>
            </Row>
        </Container>
    );
}

export default function BlackJack() {
    return (
        <Container>
            <Jumbotron>
                <h1 className="center">Welcome To Blackjack</h1>
            </Jumbotron>
            <div>{Hand()}</div>
        </Container>
    );
}
