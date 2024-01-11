package event;

import consumer.device.Device;
import event.throwStrategy.EventThrowStrategy;
import place.Room;
import smarthome.Simulation;
import utils.Priority;

import java.time.LocalDateTime;

/**
 * Events provides interaction from devices to people. Deice throws an event and person
 * detects and solves it. Events hold information about by what, when and where in was created.
 */
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

    /**
     * Throw event to room/floor/home. Depends on throw strategy.
     */
    public void throwEvent() {
        eventType.getThrowStrategy().throwEvent(this);
    }

    public EventType getEventType() {
        return eventType;
    }

    public Device getCreator() {
        return creator;
    }

    public Priority getPriority() {
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
