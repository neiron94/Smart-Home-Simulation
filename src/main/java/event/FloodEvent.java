package event;

import consumer.device.Device;
import event.throwStrategy.EventThrowStrategy;
import event.throwStrategy.RoomThrowStrategy;

import java.util.Date;

public class FloodEvent extends Event {

    public FloodEvent(Device creator, Room origin) {
        super(EventType.FLOOD, EventPriority.HIGH, new RoomThrowStrategy(), Simulation.getInstance().getDate(), creator, origin);
    }
}
