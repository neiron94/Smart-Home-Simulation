package consumer.device.sensored.sensor;

import consumer.device.sensored.EventDevice;
import event.EventType;

public class GasSensor extends EventSensor {
    public GasSensor() {
        super(EventType.LEAK);
    }
}
