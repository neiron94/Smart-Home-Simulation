/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer.device.sensored.sensor;

import event.EventType;

/**
 * Sensor which looks for a FloodEvent in the room.
 */
public class FloodSensor extends EventSensor {
    public FloodSensor() {
        super(EventType.FLOOD);
    }
}
