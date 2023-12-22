package event.throwStrategy;

import event.Event;
import place.Room;

public class RoomThrowStrategy implements EventThrowStrategy {
    @Override
    public void throwEvent(Event event, Room room) {
        event.setDestination(room);
        event.getDestination().addEvent(event);
    }
}
