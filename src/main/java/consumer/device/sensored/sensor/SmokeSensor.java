package consumer.device.sensored.sensor;

import event.EventType;

/**
 * Sensor which looks for a FireEvent in the room.
 */
public class SmokeSensor extends EventSensor {
    public SmokeSensor() {
        super(EventType.FIRE);
    }
}
