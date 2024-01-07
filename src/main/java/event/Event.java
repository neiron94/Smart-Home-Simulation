package event;

import consumer.device.Device;
import event.throwStrategy.EventThrowStrategy;
import place.Room;
import smarthome.Simulation;

import java.time.LocalDateTime;

public abstract class Event {
    private final EventType eventType;
    private final Device creator;
    private final Room origin;
    private final LocalDateTime eventDate;

    protected Event(EventType eventType, Device creator, Room origin) {
        this.eventType = eventType;
        this.creator = creator;
        this.origin = origin;
        eventDate = Simulation.getInstance().getCurrentTime();
    }

    public void throwEvent() {
        eventType.getThrowStrategy().throwEvent(this);
    }

    public EventType getEventType() {
        return eventType;
    }

    public Device getCreator() {
        return creator;
    }

    public EventPriority getPriority() {
        return eventType.getPriority();
    }

    public Room getOrigin() {
        return origin;
    }

    public EventThrowStrategy getThrowStrategy() {
        return eventType.getThrowStrategy();
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }
}
