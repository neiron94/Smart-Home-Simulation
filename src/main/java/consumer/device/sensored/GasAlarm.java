/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer.device.sensored;

import consumer.device.DeviceType;
import consumer.device.sensored.sensor.GasSensor;
import place.Room;

/**
 * Fire alarm for alerting about GasEvent.
 */
public class GasAlarm extends Alarm<GasSensor> {
    public GasAlarm(int id, Room startRoom) {
        super(DeviceType.GAS_ALARM, id, startRoom);
        this.sensor = new GasSensor();
        this.sensor.attachDevice(this);
    }

    @Override
    public GasAlarm copy() {
        return new GasAlarm(id, room);
    }
}
