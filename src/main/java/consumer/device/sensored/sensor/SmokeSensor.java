package consumer.device.sensored.sensor;

import consumer.device.sensored.EventDevice;
import event.EventType;

/**
 * Sensor which looks for a FireEvent in the room.
 */
public class SmokeSensor extends EventSensor {
    public SmokeSensor() {
        super(EventType.FIRE);
    }
}
