import React from 'react';
import Select from 'react-select';

export default class ReactAnswer extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            selectedOption: null,
            isDisabled: false,
            feedbackText: ""
        };
        this.handleChange = this.handleChange.bind(this);
        this.isDisabled = this.isDisabled.bind(this);
        this.isEnabled = this.isEnabled.bind(this);
        this.checkAnswer = this.checkAnswer.bind(this);
    };

    handleChange(selectedOption){
        this.setState(
            { selectedOption },
            () => console.log(`Option selected:`, this.state.selectedOption)
        );
    };

    isDisabled() {
        this.setState(state => ({ isDisabled: true }));
    }

    isEnabled() {
        this.setState(state => ({ isDisabled: false }));
    }

    checkAnswer(){
        if(this.state.selectedOption !== null && this.state.isDisabled !== true){
            if (this.state.selectedOption.label === this.props.correctAnswer){
                this.setState(state => ({ feedbackText: "Correct"}));
                console.log('answer: correct');
            } else {
                this.setState(state => ({ feedbackText: "Incorrect"}));
                console.log('answer: incorrect');
            }
        }
    }

    render() {
        const { selectedOption } = this.state;

        return (
            <div>
                <Select
                    value={selectedOption}
                    onChange={this.handleChange}
                    options={this.props.options}
                    isSearchable={false}
                    isDisabled={this.state.isDisabled}
                />
                <p>{this.state.feedbackText}</p>
                <button className="btn btn-login" onClick={this.isDisabled}>
                    Disable
                </button>
                <button className="btn btn-login" onClick={this.isEnabled}>
                    Enable
                </button>
                <button className="btn btn-login" onClick={this.checkAnswer}>
                    Check Answer
                </button>
            </div>
        );
    }
}