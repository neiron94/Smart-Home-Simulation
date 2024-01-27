/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package event;

import consumer.device.Device;
import place.Room;

/**
 * Event notifies about the water leaking device.
 * Is throw to room by flooding device.
 */
public class FloodEvent extends Event {

    public FloodEvent(Device creator, Room origin) {
        super(EventType.FLOOD, creator, origin);
    }
}
