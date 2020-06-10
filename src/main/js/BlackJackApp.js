import React from "react";
import GameChooser from "./GameChooser";
import BlackJackGame from "./BlackJackGame";

class BlackJackApp extends React.Component {
    constructor(props) {
        super(props);
        this.Pages = {
            LOGIN: 1,
            GAME: 2
        };

        this.handleReturnToLastGame = this.handleReturnToLastGame.bind(this);
        this.state = {
            gameId: null,
            currPage: this.Pages.LOGIN
        };
    }

    handleReturnToLastGame(gameId) {
        this.setState({
            gameId:gameId,
            currPage: this.Pages.GAME
        });
    }

    render() {
        if (this.state.currPage === this.Pages.LOGIN) {
            return <GameChooser onReturnToGameClick={this.handleReturnToLastGame} />;
        } else if (this.state.currPage === this.Pages.GAME) {
            return <BlackJackGame gameId={this.state.gameId} />;
        } else {
            return "ERROR: Bad state in BlackJackApp.render";
        }
    }
}

export default function App() {
    return <BlackJackApp />;
}
