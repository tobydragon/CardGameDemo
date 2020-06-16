import { expect } from 'chai';
import { shallow, mount, render } from 'enzyme';
import React from "react";

import Hand from "../../main/js/Hand"
import Card from "react-bootstrap/Card";

const cards= [
    { suit: "spades", value: 2 },
    { suit: "hearts", value: 4 },
    { suit: "diamonds", value: 6 },
    { suit: "hearts", value: 5 }
]
describe('Hand', () => {
    it('check hand for right number of cards', () => {
        const handResult = shallow(<Hand cards={cards}/>);
        // console.log(handResult.debug());
        // console.log(handResult.find(Card).debug());
        expect(handResult.find(Card)).to.have.lengthOf(4);
    });
});