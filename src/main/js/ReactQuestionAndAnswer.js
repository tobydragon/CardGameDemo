import React from 'react';
import ReactQuestion from "./ReactQuestion";
import ReactAnswer from "./ReactAnswer";

export default class ReactQuestionAndAnswer extends React.Component {
    constructor(props) {
        super(props);
    }

    makeOptionsForSelection(labelList){
        let toReturn = [];
        for (let x of labelList){
            toReturn.push({
                value: x,
                label: x
            });
        }
        return toReturn;
    }

    render(){
        return(
            <div id={"QuestionAndAnswerController"}>
                <ReactQuestion questionID={this.props.data.id} questionText={this.props.data.questionText}/>
                <ReactAnswer options={this.makeOptionsForSelection(this.props.data.possibleAnswers)} correctAnswer={this.props.data.correctAnswer}/>
            </div>
            );
    }
}