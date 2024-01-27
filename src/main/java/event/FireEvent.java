/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package event;

import consumer.device.Device;
import place.Room;

/**
 * Event notifies about the electric device caught fire.
 * Is throw to room by burning device.
 */
public class FireEvent extends Event {
    public FireEvent(Device creator, Room origin) {
        super(EventType.FIRE, creator, origin);
    }
}
