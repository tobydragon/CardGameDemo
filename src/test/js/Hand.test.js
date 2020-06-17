import { expect } from 'chai';
import { shallow, mount, render } from 'enzyme';
import React from "react";

import Card from "react-bootstrap/Card";

import Hand from "../../main/js/Hand"
import {regHandEx, bigHandEx, singleCardHandEx} from "./Hand.examples";

describe('Hand', () => {
    it('check different Hands', () => {
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

        handResult = shallow(<Hand cards={singleCardHandEx}/>);
        expect(handResult.find(Card)).to.have.lengthOf(1);

        handResult = shallow(<Hand cards={[]} />);
        expect(handResult.find(Card)).to.have.lengthOf(0);

    });
});