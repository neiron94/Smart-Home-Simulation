package event;

import consumer.device.Device;
import event.throwStrategy.HomeThrowStrategy;
import smarthome.Simulation;
import place.Room;

public class FillEvent extends Event {
    public FillEvent(Device creator, Room origin) {
        super(EventType.FILL, creator, origin);
    }
}
