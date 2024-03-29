/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer.device.sensored.sensor;

import consumer.device.sensored.SensoredDevice;

/**
 * Sensor for working with sensored device.
 * @param <T> Sensored device for working with sensor.
 */
public abstract class Sensor<T extends SensoredDevice<?>> {
    protected T device;

    /**
     * Checks info for sensored device.
     */
    public abstract void checkInfo();

    /**
     * Attach device to this sensor.
     * @param device device to attach
     */
    public void attachDevice(T device) {
        if (this.device == null)
            this.device = device;
        else
            throw new IllegalArgumentException("Device is already attached");
    }
}
