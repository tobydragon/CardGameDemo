import renderer from "react-test-renderer";
import React, { useState } from "react";
import PlayerHand from "../../main/js/PlayerHand";
import {regHandEx} from "./Hand.examples";
import Round from "../../main/js/Round";

// let playerHandResult = shallow(<PlayerHand ownername={"player"} cards={regHandEx}/>);


test('PLAYING state test', () => {
    const component = renderer.create(<PlayerHand gameState={"PLAYING"} ownername={"player"} cards={regHandEx}/>);
    let tree = component.toJSON();
    expect(tree).toMatchSnapshot();
});

test('not PLAYING state test', () =>{
    const component = renderer.create(<PlayerHand ownername={"player"} cards={[]} />);
    let tree = component.toJSON();
    expect(tree).toMatchSnapshot();
});