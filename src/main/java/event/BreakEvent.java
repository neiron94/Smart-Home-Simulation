package event;

import consumer.device.Device;
import event.throwStrategy.RoomThrowStrategy;

public class BreakEvent extends Event {

    public BreakEvent(Device creator, Room origin) {
        super(EventType.BREAK, EventPriority.LOW, new RoomThrowStrategy(), Simulation.getInstance().getDate(), creator, origin);
    }
}
