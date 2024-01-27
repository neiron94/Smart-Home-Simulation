/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package event;

import consumer.device.Device;
import place.Room;

/**
 * Event notifies about breakdown of device.
 * Is throw to room by broken device.
 */
public class BreakEvent extends Event {
    public BreakEvent(Device creator, Room origin) {
        super(EventType.BREAK, creator, origin);
    }
}
