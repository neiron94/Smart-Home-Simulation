/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer.device.sensored;

import consumer.device.DeviceType;
import consumer.device.sensored.sensor.FloodSensor;
import place.Room;

/**
 * Fire alarm for alerting about FloodEvent.
 */
public class FloodAlarm extends Alarm<FloodSensor> {
    public FloodAlarm(int id, Room startRoom) {
        super(DeviceType.FLOOD_ALARM, id, startRoom);
        this.sensor = new FloodSensor();
        this.sensor.attachDevice(this);
    }

    @Override
    public FloodAlarm copy() {
        return new FloodAlarm(id, room);
    }
}
