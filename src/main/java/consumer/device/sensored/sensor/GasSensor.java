package consumer.device.sensored.sensor;

import event.EventType;

/**
 * Sensor which looks for a LeakEvent in the room.
 */
public class GasSensor extends EventSensor {
    public GasSensor() {
        super(EventType.LEAK);
    }
}
