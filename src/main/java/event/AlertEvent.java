/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package event;

import consumer.device.Device;
import place.Room;

/**
 * Event notifies about another disaster event (fire/flood/leak).
 * Is throw to home by alarm device.
 */
public class AlertEvent extends Event {
    public AlertEvent(Device creator, Room origin) {
        super(EventType.ALERT, creator, origin);
    }
}
