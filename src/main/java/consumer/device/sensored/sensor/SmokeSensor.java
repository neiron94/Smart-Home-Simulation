/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer.device.sensored.sensor;

import event.EventType;

/**
 * Sensor which looks for a FireEvent in the room.
 */
public class SmokeSensor extends EventSensor {
    public SmokeSensor() {
        super(EventType.FIRE);
    }
}
