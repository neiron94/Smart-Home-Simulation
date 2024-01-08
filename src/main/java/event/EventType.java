package event;

import event.throwStrategy.*;
import utils.Priority;

public enum EventType {
    FLOOD("Flood", FloodEvent.class, Priority.EVENT_HIGH, new RoomThrowStrategy()),
    LEAK("Leak", LeakEvent.class, Priority.EVENT_HIGH, new RoomThrowStrategy()),
    FIRE("Fire", FireEvent.class, Priority.EVENT_HIGH, new RoomThrowStrategy()),
    BREAK("Brake", BreakEvent.class, Priority.EVENT_LOW, new RoomThrowStrategy()),
    WAKEUP("Wakeup", WakeUpEvent.class, Priority.EVENT_LOW, new RoomThrowStrategy()),
    ALERT("Alert", AlertEvent.class, Priority.EVENT_MEDIUM, new HomeThrowStrategy()),
    FILL("Fill", FillEvent.class, Priority.EVENT_LOW, new HomeThrowStrategy());

    private final String description;
    private final Class<? extends Event> eventClass;
    private final Priority priority;
    private final EventThrowStrategy throwStrategy;

    EventType(String description, Class<? extends Event> eventClass, Priority priority, EventThrowStrategy throwStrategy) {
        this.description = description;
        this.eventClass = eventClass;
        this.priority = priority;
        this.throwStrategy = throwStrategy;
    }

    public String getDescription() {
        return description;
    }

    public Class<? extends Event> getEventClass() {
        return eventClass;
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
