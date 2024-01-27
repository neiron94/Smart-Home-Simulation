/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer.device.sensored;

import consumer.device.DeviceType;
import consumer.device.sensored.sensor.LightSensor;
import place.Room;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;

/**
 * Window for decreasing room's brightness.
 */
public class Window extends ParameterDevice<LightSensor> {

    public Window(int id, Room startRoom) {
        super(DeviceType.WINDOW, id, startRoom);
        this.sensor = new LightSensor();
        this.sensor.attachDevice(this);
    }

    /**
     * Increase or decrease power depending on given parameter ond preferred parameter
     * in Control Panel of the room.
     * @param parameter Changed room parameter.
     */
    @Override
    public void react(Number parameter) {
        if (room.getBrightness() > room.getControlPanel().getBrightness())
            setPower(power + 1);
        else if (room.getBrightness() < room.getControlPanel().getBrightness())
            setPower(power - 1);
    }

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.WINDOW * power / 100);
    }

    @Override
    public Window copy() {
        return new Window(id, room);
    }
}