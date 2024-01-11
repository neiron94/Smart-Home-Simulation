package event;

import consumer.device.Device;
import place.Room;

/**
 * Event notifies about emptiness.
 * Is throw to home by feeder device.
 */
public class FillEvent extends Event {
    public FillEvent(Device creator, Room origin) {
        super(EventType.FILL, creator, origin);
    }
}
