package event;

import consumer.device.Device;
import event.throwStrategy.EventThrowStrategy;
import place.Room;
import smarthome.Simulation;

import java.time.LocalDateTime;

public abstract class Event {
    private final EventType eventType;
    private final Device creator;
    private final EventPriority priority;
    private final Room origin;
    private final EventThrowStrategy throwStrategy;
    private final LocalDateTime eventDate;

    protected Event(EventType eventType, EventPriority priority, EventThrowStrategy throwStrategy, Device creator, Room origin) {
        this.eventType = eventType;
        this.creator = creator;
        this.priority = priority;
        this.throwStrategy = throwStrategy;
        this.origin = origin;
        eventDate = Simulation.getInstance().getCurrentTime();
    }

    public void throwEvent() {
        throwStrategy.throwEvent(this);
    }

    public EventType getEventType() {
        return eventType;
    }

    public Device getCreator() {
        return creator;
    }

    public EventPriority getPriority() {
        return priority;
    }

    public Room getOrigin() {
        return origin;
    }

    public EventThrowStrategy getThrowStrategy() {
        return throwStrategy;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }
}
