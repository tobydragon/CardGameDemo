import React from 'react';
import { shallow } from 'enzyme';
import BlackJackGame from '../../main/js/BlackJackGame.js';


describe('First React component test with Enzyme', () => {
    it('renders without crashing', () => {
        shallow(<BlackJackGame/>);
    });
});