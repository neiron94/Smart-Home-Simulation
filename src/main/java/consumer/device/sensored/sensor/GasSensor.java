package consumer.device.sensored.sensor;

import consumer.device.sensored.EventDevice;
import jdk.jfr.EventType;

public class GasSensor extends EventSensor {
    protected GasSensor(EventDevice<?> device) {
        super(device, EventType.LEAK);
    }
}
