package event;

import consumer.device.Device;
import event.throwStrategy.HomeThrowStrategy;
import smarthome.Simulation;
import place.Room;

public class AlertEvent extends Event {
    public AlertEvent(Device creator, Room origin) {
        super(EventType.ALERT, EventPriority.MEDIUM, new HomeThrowStrategy(), Simulation.getInstance().getCurrentTime(), creator, origin);
    }
}
