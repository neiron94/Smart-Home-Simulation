package event.throwStrategy;

import event.Event;
import smarthome.Simulation;

public class FloorThrowStrategy implements EventThrowStrategy {
    @Override
    public void throwEvent(Event event) {
        Simulation.getInstance().getHome().getFloors().stream()
                .filter(floor -> floor.getRooms().contains(event.getOrigin()))   // Find floor where event was created
                .findFirst()
                .orElseThrow()      // TODO - process exception?
                .addEvent(event);
    }
}
