package event.throwStrategy;

import event.Event;
import smarthome.Simulation;

public class RoomThrowStrategy implements EventThrowStrategy {
    @Override
    public void throwEvent(Event event) {
        event.getOrigin().addEvent(event);
    }
}
