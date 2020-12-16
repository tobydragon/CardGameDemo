import { expect } from 'chai';
import { shallow, mount, render } from 'enzyme';
import React from "react";
import ReactQuestion from "../../main/js/ReactQuestion";

describe('react question', () => {
    it('question text', () => {
        const component = shallow(<ReactQuestion />);
        let questionText = component.find('div');
        expect(questionText.length).equal(1);
    })
})