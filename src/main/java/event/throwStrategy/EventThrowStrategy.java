package event.throwStrategy;

import event.Event;

public interface EventThrowStrategy {
    void throwEvent(Event event, Room room);
}
