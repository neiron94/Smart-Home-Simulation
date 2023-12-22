package event;

import consumer.device.Device;
import event.throwStrategy.RoomThrowStrategy;

public class WakeUpEvent extends Event {
    public WakeUpEvent(Device creator, Room origin) {
        super(EventType.WAKEUP, EventPriority.LOW, new RoomThrowStrategy(), Simulation.getInstance().getDate(), creator, origin);
    }
}
