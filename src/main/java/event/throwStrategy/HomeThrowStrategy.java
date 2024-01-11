package event.throwStrategy;

import event.Event;
import smarthome.Simulation;

/**
 * Throws event to a home, where creator of event is located.
 */
public class HomeThrowStrategy implements EventThrowStrategy {
    @Override
    public void throwEvent(Event event) {
        Simulation.getInstance().getHome().addEvent(event);
    }
}
