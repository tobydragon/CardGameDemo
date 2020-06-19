import React from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";


export default class GameChooserForm extends React.Component {
    constructor(props) {
        super(props);
        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleReturnToGameClick = this.handleReturnToGameClick.bind(this);
        this.handleNewGameClick = this.handleNewGameClick.bind(this);
        this.state={
            userName: "",
            gameName: ""
        }
    }

    /**
     * in order for this to work, the name attribute of the input fields needs to be set to the name
     * of the state variable that will be synced with the input field by this method
     */
    handleInputChange(event) {
        const target = event.target;
        const name = target.name;
        const value = target.value;
        // console.log("GameChooserForm.handleInputEvent - value changed:"+name+":"+value)
        this.setState({
            [name]: value
        });
    }

    handleReturnToGameClick(e) {
        this.props.onReturnToGameClick(this.state.gameName);
    }

    handleNewGameClick(e){
        this.props.onNewGameClick(this.state.userName);
    }

    render() {
        return (
            <Form>
                <Form.Group controlId="formusername">
                    <Form.Label>New Game</Form.Label>
                    <Form.Control onChange={this.handleInputChange} name="userName" type="text" placeholder="Enter username" />
                </Form.Group>
                <Button onClick={this.handleNewGameClick} variant="primary">
                    New Game
                </Button>
                <Form.Group controlId="formgamename">
                    <Form.Label>Return to Game</Form.Label>
                    <Form.Control onChange={this.handleInputChange} name="gameName" type="text" placeholder="Enter Game name" />
                </Form.Group>
                <Button onClick={this.handleReturnToGameClick} variant="primary">
                    Return to Game
                </Button>
            </Form>
        );
    }
}
//
// export default function GameChooser(props) {
//     return new GameChooserForm(props);
// }
