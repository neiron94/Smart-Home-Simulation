package event.throwStrategy;

import event.Event;

public class RoomThrowStrategy implements EventThrowStrategy {
    @Override
    public void throwEvent(Event event, Room room) {
        event.setDestination(room);
        event.getDestination().addEvent(event);
    }
}
