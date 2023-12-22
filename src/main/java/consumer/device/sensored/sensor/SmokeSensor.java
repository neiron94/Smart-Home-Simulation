package consumer.device.sensored.sensor;

import consumer.device.sensored.EventDevice;
import jdk.jfr.EventType;

public class SmokeSensor extends EventSensor {
    protected SmokeSensor(EventDevice<?> device) {
        super(device, EventType.FIRE);
    }
}
