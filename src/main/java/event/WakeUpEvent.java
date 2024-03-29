/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package event;

import consumer.device.Device;
import place.Room;

/**
 * Event notifies about working alarm clock.
 * Is throw to room by alarm clock.
 */
public class WakeUpEvent extends Event {
    public WakeUpEvent(Device creator, Room origin) {
        super(EventType.WAKEUP, creator, origin);
    }
}
