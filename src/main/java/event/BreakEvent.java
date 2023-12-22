package event;

import consumer.device.Device;
import event.throwStrategy.RoomThrowStrategy;
import main.Simulation;
import place.Room;

public class BreakEvent extends Event {

    public BreakEvent(Device creator, Room origin) {
        super(EventType.BREAK, EventPriority.LOW, new RoomThrowStrategy(), Simulation.getInstance().getCurrentTime(), creator, origin);
    }
}
