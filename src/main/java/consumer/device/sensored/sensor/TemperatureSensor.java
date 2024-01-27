/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer.device.sensored.sensor;

/**
 * Sensor for checking temperature of the room.
 */
public class TemperatureSensor extends ParameterSensor {

    /**
     * Check temperature of the room and send it to attached device.
     */
    @Override
    public void checkInfo() {
        device.react(device.getRoom().getTemperature());
    }
}
