import React from "react";

import Jumbotron from "react-bootstrap/Jumbotron";
import Container from "react-bootstrap/Container";
import Button from "react-bootstrap/Button";
import ButtonToolbar from "react-bootstrap/ButtonToolbar";
import CardGroup from "react-bootstrap/CardGroup";
import Card from "react-bootstrap/Card";

const BlackJackGame = () => (
    <Container>
        <Jumbotron>
            <h1 className="center">Welcome To Blackjack</h1>
        </Jumbotron>
        <div
            style={{
                display: "flex",
                justifyContent: "center",
                alignItems: "center"
            }}
        >
            <Hand />
        </div>
        <div
            style={{
                display: "flex",
                justifyContent: "center",
                alignItems: "center"
            }}
        >
            <Buttons />
        </div>
    </Container>
);

const Buttons = () => (
    <ButtonToolbar>
        <Button>Hit</Button>
        <Button>Stand</Button>
    </ButtonToolbar>
);

const Hand = () => (
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

export default BlackJackGame;