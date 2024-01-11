package event;

import event.throwStrategy.*;
import utils.Priority;

/**
 * This enum holds information about different types of events, such as
 * priority and throw destination.
 */
public enum EventType {
    FLOOD("Flood", Priority.EVENT_HIGH, new RoomThrowStrategy()),
    LEAK("Leak", Priority.EVENT_HIGH, new RoomThrowStrategy()),
    FIRE("Fire", Priority.EVENT_HIGH, new RoomThrowStrategy()),
    BREAK("Brake", Priority.EVENT_LOW, new RoomThrowStrategy()),
    WAKEUP("Wakeup", Priority.EVENT_MEDIUM, new RoomThrowStrategy()),
    ALERT("Alert", Priority.EVENT_MEDIUM, new HomeThrowStrategy()),
    FILL("Fill", Priority.EVENT_LOW, new HomeThrowStrategy());

    private final String description;
    private final Priority priority;
    private final EventThrowStrategy throwStrategy;

    EventType(String description, Priority priority, EventThrowStrategy throwStrategy) {
        this.description = description;
        this.priority = priority;
        this.throwStrategy = throwStrategy;
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public EventThrowStrategy getThrowStrategy() {
        return throwStrategy;
    }

    @Override
    public String toString() {
        return String.format("%s Event", description);
    }
}
