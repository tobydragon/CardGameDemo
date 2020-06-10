import React from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";

class GameChooserForm extends React.Component {
    constructor(props) {
        super(props);
        this.handleReturnToGameClick = this.handleReturnToGameClick.bind(this);
    }

    handleReturnToGameClick(e) {
        this.props.onReturnToGameClick();
    }

    render() {
        return (
            <Form>
                <Form.Group controlId="formusername">
                    <Form.Label>New Game</Form.Label>
                    <Form.Control type="text" placeholder="Enter username" />
                </Form.Group>
                <Button variant="primary">New Game</Button>
                <Form.Group controlId="formgamename">
                    <Form.Label>Return to Game</Form.Label>
                    <Form.Control type="text" placeholder="Enter Game name" />
                </Form.Group>
                <Button onClick={this.handleReturnToGameClick} variant="primary">
                    Return to Game
                </Button>
            </Form>
        );
    }
}

export default function GameChooser(props) {
    return <GameChooserForm onReturnToGameClick={props.onReturnToGameClick} />;
}
