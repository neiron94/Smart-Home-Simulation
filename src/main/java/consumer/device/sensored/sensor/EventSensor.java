package consumer.device.sensored.sensor;

import consumer.device.sensored.EventDevice;
import event.Event;
import event.EventType;

/**
 * Sensor for detecting events.
 */
public abstract class EventSensor extends Sensor<EventDevice<?>> {

    protected final EventType eventType;

    protected EventSensor(EventType eventType) {
        this.eventType = eventType;
    }

    /**
     * Look for an event in the room and send info to attached device.
     */
    @Override
    public void checkInfo() {
        // Look for a eventType event in this room, if exists -> device.react() (only once, even if more events detected)
        for (Event event : device.getRoom().getEvents()) {
            if (eventType == event.getEventType()) {
                device.react();
                return;
            }
        }
    }
}
