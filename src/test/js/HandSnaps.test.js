import renderer from "react-test-renderer";
import React from "react";

import Hand from "../../main/js/Hand";
import {regHandEx} from "./Hand.examples";

test('hand snapshots test', () => {
    const component = renderer.create(<Hand cards={regHandEx}/>);
    let tree = component.toJSON();
    expect(tree).toMatchSnapshot();
});