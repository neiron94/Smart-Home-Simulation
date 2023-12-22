package event;

import consumer.device.Device;
import event.throwStrategy.RoomThrowStrategy;

public class FireEvent extends Event {

    public FireEvent(Device creator, Room origin) {
        super(EventType.FIRE, EventPriority.HIGH, new RoomThrowStrategy(), Simulation.getInstance().getDate(), creator, origin);
    }
}
