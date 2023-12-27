package event;

import consumer.device.Device;
import event.throwStrategy.EventThrowStrategy;
import place.EventDestination;
import place.Room;

import java.util.Date;

public abstract class Event {
    private final EventType eventType;
    private final Device creator;
    private final EventPriority priority;
    private final Room origin;
    private final EventThrowStrategy throwStrategy;
    private final Date eventDate;
    private Date solveDate; // TODO - move to Person

    protected Event(EventType eventType, EventPriority priority, EventThrowStrategy throwStrategy, Date eventDate, Device creator, Room origin) {
        this.eventType = eventType;
        this.creator = creator;
        this.priority = priority;
        this.throwStrategy = throwStrategy;
        this.eventDate = eventDate;
        this.origin = origin;
    }

    public void throwEvent() {
        throwStrategy.throwEvent(this);
    }

    public Date getSolveDate() {
        return solveDate;
    }

    public void setSolveDate(Date solveDate) {
        this.solveDate = solveDate;
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

    public Date getEventDate() {
        return eventDate;
    }
}
