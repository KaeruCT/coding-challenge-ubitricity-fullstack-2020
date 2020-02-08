import { mount } from 'enzyme';
import React from 'react';

import ChargingPoint from './ChargingPoint';

describe('components/ChargingPointsWidget/ChargingPoint', () => {
    const props = {
        id: 1,
        amps: 20,
        occupied: true,
        fastCharging: true,
        connect: jest.fn(),
        disconnect: jest.fn(),
    }
    it('should render a ChargingPoint', () => {
        const chargingPoint = mount(<ChargingPoint {...props} />);
        expect(chargingPoint.find('.charging-type').text()).toEqual('Fast');
        expect(chargingPoint.find('.amps').text()).toEqual('20A');
        expect(chargingPoint.find('button').text()).toEqual('Disconnect');
    });

    it('should call disconnect when the button is clicked', () => {
        const chargingPoint = mount(<ChargingPoint {...props} />);
        chargingPoint.find('button').simulate('click');

        expect(props.disconnect).toHaveBeenCalled();
    });
});