package event;

import consumer.device.Device;
import event.throwStrategy.RoomThrowStrategy;
import smarthome.Simulation;
import place.Room;

public class LeakEvent extends Event {

    public LeakEvent(Device creator, Room origin) {
        super(EventType.LEAK, EventPriority.HIGH, new RoomThrowStrategy(), Simulation.getInstance().getCurrentTime(), creator, origin);
    }
}
