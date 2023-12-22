package event;

import consumer.device.Device;
import event.throwStrategy.RoomThrowStrategy;

public class LeakEvent extends Event {

    public LeakEvent(Device creator, Room origin) {
        super(EventType.LEAK, EventPriority.HIGH, new RoomThrowStrategy(), Simulation.getInstance().getDate(), creator, origin);
    }
}
