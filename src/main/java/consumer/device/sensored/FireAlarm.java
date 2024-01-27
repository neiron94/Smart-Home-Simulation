/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer.device.sensored;

import consumer.device.DeviceType;
import consumer.device.sensored.sensor.SmokeSensor;
import place.Room;

/**
 * Fire alarm for alerting about FireEvent.
 */
public class FireAlarm extends Alarm<SmokeSensor> {
    public FireAlarm(int id, Room startRoom) {
        super(DeviceType.FIRE_ALARM, id, startRoom);
        this.sensor = new SmokeSensor();
        this.sensor.attachDevice(this);
    }

    @Override
    public FireAlarm copy() {
        return new FireAlarm(id, room);
    }
}
