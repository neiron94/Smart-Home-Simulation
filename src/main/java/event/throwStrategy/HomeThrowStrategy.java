package event.throwStrategy;

import event.Event;
import main.Simulation;
import place.Room;

public class HomeThrowStrategy implements EventThrowStrategy {
    @Override
    public void throwEvent(Event event, Room room) {
        event.setDestination(Simulation.getInstance().getHome());
        event.getDestination().addEvent(event);
    }
}
