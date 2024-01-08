package event.throwStrategy;

import event.Event;
import smarthome.Simulation;

public class FloorThrowStrategy implements EventThrowStrategy {
    @Override
    public void throwEvent(Event event) {
        event.getOrigin().getFloor().addEvent(event);
    }
}
