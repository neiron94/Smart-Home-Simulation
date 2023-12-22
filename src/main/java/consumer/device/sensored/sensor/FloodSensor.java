package consumer.device.sensored.sensor;

import consumer.device.sensored.EventDevice;
import event.EventType;

public class FloodSensor extends EventSensor {
    public FloodSensor(EventDevice<?> device) {
        super(device, EventType.FLOOD);
    }
}
