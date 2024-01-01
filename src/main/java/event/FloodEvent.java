package event;

import consumer.device.Device;
import event.throwStrategy.RoomThrowStrategy;
import smarthome.Simulation;
import place.Room;

public class FloodEvent extends Event {

    public FloodEvent(Device creator, Room origin) {
        super(EventType.FLOOD, EventPriority.HIGH, new RoomThrowStrategy(), creator, origin);
    }
}
