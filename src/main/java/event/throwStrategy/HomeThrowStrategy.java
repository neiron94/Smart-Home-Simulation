package event.throwStrategy;

import event.Event;
import smarthome.Simulation;

public class HomeThrowStrategy implements EventThrowStrategy {
    @Override
    public void throwEvent(Event event) {
        Simulation.getInstance().getHome().addEvent(event);
    }
}
