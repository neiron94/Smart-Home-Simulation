package event;

import consumer.device.Device;
import place.Room;

/**
 * Event notifies about the gas leaking device.
 * Is throw to room by gas leaking device.
 */
public class LeakEvent extends Event {
    public LeakEvent(Device creator, Room origin) {
        super(EventType.LEAK, creator, origin);
    }
}
