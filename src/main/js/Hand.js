import Card from "react-bootstrap/Card";
import CardGroup from "react-bootstrap/CardGroup";
import React from "react";
import {Container} from "react-bootstrap";

export default function Hand(props) {
    //using react-bootstrap data structure called Card, not to be confused with also talking about playing cards
    const cardsArray = props.cards.map(aCardModel => (
        <Card key={""+aCardModel.value+aCardModel.suit}>
            <Card.Title>{aCardModel.value}</Card.Title>
            <Card.Title> of</Card.Title>
            <Card.Title>{aCardModel.suit}</Card.Title>
        </Card>
    ));
    return (
        <Container>
            {props.ownerName}
            <CardGroup className="cardDeck">{cardsArray}</CardGroup>
        </Container>

    );
}