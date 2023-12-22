package event;

import consumer.device.Device;
import event.throwStrategy.HomeThrowStrategy;
import main.Simulation;
import place.Room;

public class FillEvent extends Event {
    public FillEvent(Device creator, Room origin) {
        super(EventType.FILL, EventPriority.LOW, new HomeThrowStrategy(), Simulation.getInstance().getCurrentTime(), creator, origin);
    }
}
