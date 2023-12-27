package event;

import consumer.device.Device;
import event.throwStrategy.RoomThrowStrategy;
import smarthome.Simulation;
import place.Room;

public class FireEvent extends Event {

    public FireEvent(Device creator, Room origin) {
        super(EventType.FIRE, EventPriority.HIGH, new RoomThrowStrategy(), Simulation.getInstance().getCurrentTime(), creator, origin);
    }
}
