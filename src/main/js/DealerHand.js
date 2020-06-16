import React from "react";
import {getFromServer, postToServer} from "./Comm";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Container from "react-bootstrap/Container";
import Hand from "./Hand";

export default class DealerHand extends React.Component{
    constructor(props) {
        super(props);
        this.state={
            dealerCards: [
                { suit: "spades", value: 5 },
                { suit: "diamonds", value: 10 },
            ]
        }
    }


    render(){
        return (
            <Container>
                <Row>
                    <Col>
                        <Hand ownerName="Dealer" cards={this.state.dealerCards}/>
                    </Col>
                </Row>
            </Container>
        );
    }
}