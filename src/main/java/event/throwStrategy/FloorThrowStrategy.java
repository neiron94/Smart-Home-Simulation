package event.throwStrategy;

import event.Event;
import main.Simulation;
import place.Room;

public class FloorThrowStrategy implements EventThrowStrategy {
    @Override
    public void throwEvent(Event event, Room room) {
        // TODO - write without destination?
        event.setDestination(Simulation.getInstance().getHome().getFloors().stream().filter(floor -> floor.getRooms().contains(room)).findFirst().get());
        event.getDestination().addEvent(event);
    }
}
