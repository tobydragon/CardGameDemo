import React from "react";
import GameChooserForm from "./GameChooser";
import BlackJackGame from "./BlackJackGame";
import {postToServer} from "./Comm";
import Round from "./Round"
import ReactQuestionAndAnswer from "./ReactQuestionAndAnswer";


class BlackJackApp extends React.Component {
    constructor(props) {
        super(props);
        this.Pages = {
            LOGIN: 1,
            GAME: 2
        };

        this.handleGoToGame = this.handleGoToGame.bind(this);
        this.handleNewGameRequest = this.handleNewGameRequest.bind(this);
        this.handleNewGameServerResponse = this.handleNewGameServerResponse.bind(this);
        this.state = {
            gameId: null,
            currPage: this.Pages.LOGIN,
            baseApiUrl: "http://localhost:8080/api/blackjack"
        };
    }

    handleGoToGame(gameId) {
        this.setState({
            gameId:gameId,
            currPage: this.Pages.GAME
        });
    }

    handleNewGameRequest(userId){
        postToServer(this.state.baseApiUrl, "/newgame", {"text":userId}, this.handleNewGameServerResponse);
    }

    handleNewGameServerResponse(responseJson){
        this.handleGoToGame(responseJson.text);
    }

    render() {
        if (this.state.currPage === this.Pages.LOGIN) {
            return <GameChooserForm onReturnToGameClick={this.handleGoToGame} onNewGameClick={this.handleNewGameRequest}/>;
        } else if (this.state.currPage === this.Pages.GAME) {
            return <Round gameId={this.state.gameId} baseApiUrl={this.state.baseApiUrl} />;
        } else {
            return "ERROR: Bad state in BlackJackApp.render";
        }
    }
}

//tim= replace the return line to change what renders
export default function App() {
    const sampleQuestion1 = {
        id: "sampleQuestionID",
        questionText: "What is my favorite ice cream flavor?",
        correctAnswer: "vanilla",
        possibleAnswers: ["chocolate", "strawberry", "vanilla"]
    };

    const sampleQuestion2 = {
        id : "600-plane-./images/Annotated1BTrans.jpg",
        type : "plane",
        questionText : "On which plane is the ultrasound taken?",
        correctAnswer : "transverse (short axis)",
        possibleAnswers : [ "transverse (short axis)", "longitudinal (long axis)" ],
        imageUrl : "./images/Annotated1BTrans.jpg"
    }

    return <ReactQuestionAndAnswer data={sampleQuestion2}/>;
}
