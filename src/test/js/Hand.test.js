import { expect } from 'chai';
import { shallow, mount, render } from 'enzyme';
import React from "react";

import Hand from "../../main/js/Hand"
import Card from "react-bootstrap/Card";

const regHandEx=[
    { suit: "diamonds", value: 6 },
    { suit: "hearts", value: 4 },
    { suit: "clubs", value: 8},
]
const bigHandEx= [
    { suit: "spades", value: 2 },
    { suit: "hearts", value: 4 },
    { suit: "diamonds", value: 2 },
    { suit: "hearts", value: 5 },
    { suit: "hearts", value: 3 },
    { suit: "clubs", value: 2 },
    { suit: "hearts", value: 2 },
]
const singleCardEx= [
    { suit: "spades", value: 2 },
]

describe('Hand', () => {
    it('check hand different Hands', () => {
        let handResult = shallow(<Hand cards={regHandEx}/>);
        // console.log(handResult.debug());
        // console.log(handResult.find(Card).debug());
        expect(handResult.find(Card)).to.have.lengthOf(3);
        expect(handResult.find(Card).get(1).key).to.equal("4hearts");

        handResult = shallow(<Hand cards={bigHandEx}/>);
        expect(handResult.find(Card)).to.have.lengthOf(7);
        expect(handResult.find(Card).get(0).key).to.equal("2spades");
        expect(handResult.find(Card).get(3).key).to.equal("5hearts");
        expect(handResult.find(Card).get(6).key).to.equal("2hearts");

        handResult = shallow(<Hand cards={singleCardEx}/>);
        expect(handResult.find(Card)).to.have.lengthOf(1);

        handResult = shallow(<Hand cards={[]} />);
        expect(handResult.find(Card)).to.have.lengthOf(0);

    });
});