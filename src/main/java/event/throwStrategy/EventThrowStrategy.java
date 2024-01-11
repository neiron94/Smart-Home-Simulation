package event.throwStrategy;

import event.Event;

/**
 * Strategy for throwing events.
 */
public interface EventThrowStrategy {
    void throwEvent(Event event);
}
