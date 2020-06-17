import renderer from "react-test-renderer";
import React from "react";

import Hand from "../../main/js/Hand";
import {regHandEx, bigHandEx, singleCardHandEx} from "./Hand.examples";

test('regHandEx snapshots test', () => {
    const component = renderer.create(<Hand cards={regHandEx}/>);
    let tree = component.toJSON();
    expect(tree).toMatchSnapshot();
});

test('bigHandEx snapshots test', () => {
    const component = renderer.create(<Hand cards={bigHandEx}/>);
    let tree = component.toJSON();
    expect(tree).toMatchSnapshot();
});

test('singleCardHandEx snapshots test', () => {
    const component = renderer.create(<Hand cards={singleCardHandEx}/>);
    let tree = component.toJSON();
    expect(tree).toMatchSnapshot();
});

test('noCardHandEx snapshots test', () => {
    const component = renderer.create(<Hand cards={[]}/>);
    let tree = component.toJSON();
    expect(tree).toMatchSnapshot();
});