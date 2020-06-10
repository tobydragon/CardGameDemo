import React from "react";
import GameChooserForm from "./GameChooser";
import BlackJackGame from "./BlackJackGame";
import {postToServer} from "./Comm";

class BlackJackApp extends React.Component {
    constructor(props) {
        super(props);
        this.Pages = {
            LOGIN: 1,
            GAME: 2
        };

        this.handleReturnToLastGame = this.handleReturnToLastGame.bind(this);
        this.handleNewGameRequest = this.handleNewGameRequest.bind(this);
        this.handleNewGameServerResponse = this.handleNewGameServerResponse.bind(this);
        this.state = {
            gameId: null,
            currPage: this.Pages.LOGIN,
            baseApiUrl: "http://localhost:8080/api/blackjack"
        };
    }

    handleReturnToLastGame(gameId) {
        this.setState({
            gameId:gameId,
            currPage: this.Pages.GAME
        });
    }

    handleNewGameRequest(userId){
        postToServer(this.state.baseApiUrl, "/newgame", userId, this.handleNewGameServerResponse);
    }

    handleNewGameServerResponse(responseJson){
        this.handleReturnToLastGame(responseJson.text);
    }

    render() {
        if (this.state.currPage === this.Pages.LOGIN) {
            return <GameChooserForm onReturnToGameClick={this.handleReturnToLastGame} onNewGameClick={this.handleNewGameRequest} />;
        } else if (this.state.currPage === this.Pages.GAME) {
            return <BlackJackGame gameId={this.state.gameId} baseApiUrl={this.state.baseApiUrl}/>;
        } else {
            return "ERROR: Bad state in BlackJackApp.render";
        }
    }
}

export default function App() {
    return <BlackJackApp />;
}
