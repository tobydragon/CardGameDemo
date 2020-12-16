import React from "react";

// TODO in the future, ReactQuestion needs to account for live-authoring questions (labeled element in JS interface)
// should be in here; otherwise a different kind of QATree / ImageTask would be needed
export default class ReactQuestion extends React.Component {
    constructor(props) {
        super(props);
    }

    render(){
        return (
            <div id={this.props.questionID}>
                <p>{this.props.questionText}</p>
            </div>
        );
    }
}