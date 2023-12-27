package event.throwStrategy;

import event.Event;
import smarthome.Simulation;

public class RoomThrowStrategy implements EventThrowStrategy {
    @Override
    public void throwEvent(Event event) {
        Simulation.getInstance().getHome().getFloors().stream()
                .flatMap(floor -> floor.getRooms().stream())
                .filter(room -> room.equals(event.getOrigin()))
                .findFirst()
                .orElseThrow().addEvent(event);     // TODO - process exception?
    }
}
