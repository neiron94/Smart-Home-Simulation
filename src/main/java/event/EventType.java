package event;

import event.throwStrategy.*;

public enum EventType {
    FLOOD("Flood", EventPriority.HIGH, new RoomThrowStrategy()),
    LEAK("Leak", EventPriority.HIGH, new RoomThrowStrategy()),
    FIRE("Fire", EventPriority.HIGH, new RoomThrowStrategy()),
    BREAK("Brake", EventPriority.LOW, new RoomThrowStrategy()),
    WAKEUP("Wakeup", EventPriority.LOW, new RoomThrowStrategy()),
    ALERT("Alert", EventPriority.MEDIUM, new HomeThrowStrategy()),
    FILL("Fill", EventPriority.LOW, new HomeThrowStrategy());

    private final String description;
    private final EventPriority priority;
    private final EventThrowStrategy throwStrategy;

    EventType(String description, EventPriority priority, EventThrowStrategy throwStrategy) {
        this.description = description;
        this.priority = priority;
        this.throwStrategy = throwStrategy;
    }

    public String getDescription() {
        return description;
    }

    public EventPriority getPriority() {
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
