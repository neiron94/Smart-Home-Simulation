package event.throwStrategy;

import event.Event;
import place.Room;

public interface EventThrowStrategy {
    void throwEvent(Event event, Room room);
}
