import React from "react";
import {getFromServer, postToServer} from "./Comm";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Container from "react-bootstrap/Container";

import Hand from "./Hand"
import PlayerHand from "./PlayerHand";
import DealerHand from "./DealerHand";

export default class Round extends React.Component {
    constructor(props) {
        super(props);
        this.handleHit = this.handleHit.bind(this);
        this.handleDeal = this.handleDeal.bind(this);
        this.handleRoundStateResponse = this.handleRoundStateResponse.bind(this);
        this.state = {
            // gameId: props.gameId,
            // apiUrl: props.baseApiUrl+"/"+props.gameId,
            playerCards: [
                { suit: "spades", value: 2 },
                { suit: "hearts", value: 4 },
                { suit: "diamonds", value: 6 },
                { suit: "hearts", value: 5 }
            ],
            dealerCards: [
                { suit: "spades", value: 5 },
                { suit: "diamonds", value: 10 },
            ],
            playerId: "Susie"
        };
    }

    componentDidMount() {
        //getFromServer(this.state.apiUrl,"",this.handleHandResponse);
    }

    handleRoundStateResponse(responseJson){
        this.setState({
            playerCards: responseJson.playerHand.cards,
            dealerCards: responseJson.dealerHand.cards
        })
    }

     handleHit() {
         postToServer(this.state.apiUrl, "/hit", "", this.handleRoundStateResponse);
     }

     handleDeal() {
         postToServer(this.state.apiUrl, "/deal", "", this.handleRoundStateResponse)
     }

     render(){
        return (
            <Container>
                <Row>
                    <Col>
                        <DealerHand ownerName="Dealer"/>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <PlayerHand ownerName={this.state.playerId}/>
                    </Col>
                </Row>
            </Container>
        );
     }
 }