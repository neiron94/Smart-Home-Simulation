package consumer.device.sensored.sensor;

import consumer.device.sensored.EventDevice;
import event.EventType;

/**
 * Sensor which looks for a FloodEvent in the room.
 */
public class FloodSensor extends EventSensor {
    public FloodSensor() {
        super(EventType.FLOOD);
    }
}
