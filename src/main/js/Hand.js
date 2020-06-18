import React from "react";
import {Container, Card, CardGroup} from "react-bootstrap";

export default function Hand(props) {
    console.log("In Hand");
    console.log(props);
    //using react-bootstrap data structure called Card, not to be confused with also talking about playing cards
    const cardsArray = props.cards.map(aCardModel => (
        <Card key={""+aCardModel.value+aCardModel.suit}>
            <Card.Title>{aCardModel.value}</Card.Title>
            <Card.Title> of</Card.Title>
            <Card.Title>{aCardModel.suit}</Card.Title>
        </Card>
    ));
    console.log("In Hand");
    console.log(props);
    return (
        <Container>
            {props.ownerName}
            <CardGroup className="cardDeck">{cardsArray}</CardGroup>
        </Container>

    );
}