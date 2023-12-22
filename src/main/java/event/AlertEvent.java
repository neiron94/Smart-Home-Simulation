package event;

import consumer.device.Device;
import event.throwStrategy.HomeThrowStrategy;
import event.throwStrategy.RoomThrowStrategy;

public class AlertEvent extends Event {
    public AlertEvent(Device creator, Room origin) {
        super(EventType.ALERT, EventPriority.MEDIUM, new HomeThrowStrategy(), Simulation.getInstance().getDate(), creator, origin);
    }
}
