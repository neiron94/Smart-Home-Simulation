/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer.device.sensored;

import consumer.device.DeviceType;
import consumer.device.sensored.sensor.TemperatureSensor;
import place.Room;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;

/**
 * AC for decreasing room's temperature.
 */
public class AC extends ParameterDevice<TemperatureSensor> {

    public AC(int id, Room startRoom) {
        super(DeviceType.AC, id, startRoom);
        this.sensor = new TemperatureSensor();
        this.sensor.attachDevice(this);
    }

    /**
     * Increase or decrease power depending on given parameter ond preferred parameter
     * in Control Panel of the room.
     * @param parameter Changed room parameter.
     */
    @Override
    public void react(Number parameter) {
        if (parameter.doubleValue() > room.getControlPanel().getTemperature())
            setPower(power + 1);
        else if (parameter.doubleValue() < room.getControlPanel().getTemperature())
            setPower(power - 1);
    }

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.AC * power / 100);
    }

    @Override
    public AC copy() {
        return new AC(id, room);
    }
}
