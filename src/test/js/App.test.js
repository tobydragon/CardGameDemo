import React from 'react';
import Enzyme, { configure, shallow, mount, render } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';
configure({ adapter: new Adapter() });
import BlackJackGame from '../../main/js/BlackJackGame.js';


describe('First React component test with Enzyme', () => {
    it('renders without crashing', () => {
        shallow(<BlackJackGame/>);
    });
});