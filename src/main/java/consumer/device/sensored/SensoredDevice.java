/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer.device.sensored;

import consumer.device.Device;
import consumer.ElectricityConsumer;
import consumer.device.DeviceType;
import consumer.device.sensored.sensor.Sensor;
import place.Room;

/**
 * Device which has sensor attached and gets information from it.
 * @param <T> Type of sensor.
 */
public abstract class SensoredDevice<T extends Sensor<?>> extends Device implements ElectricityConsumer {

    protected T sensor;

    public SensoredDevice(DeviceType type, int id, Room startRoom) {
        super(type, id, startRoom);
    }

    /**
     * Sensor check information every tick.
     * @return Can be ignored.
     */
    @Override
    public boolean routine() {
        if (!super.routine()) return false;

        sensor.checkInfo();

        return true;
    }
}