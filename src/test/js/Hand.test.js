import { shallow, mount, render } from 'enzyme';
import React from "react";

import Hand from "../../main/js/Hand"

const cards= [
    { suit: "spades", value: 2 },
    { suit: "hearts", value: 4 },
    { suit: "diamonds", value: 6 },
    { suit: "hearts", value: 5 }
]
describe('First React component test with Enzyme', () => {
    it('renders without crashing', () => {
        shallow(<Hand cards={cards}/>);
    });
});