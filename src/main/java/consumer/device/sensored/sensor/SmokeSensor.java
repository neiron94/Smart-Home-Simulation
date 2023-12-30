package consumer.device.sensored.sensor;

import consumer.device.sensored.EventDevice;
import event.EventType;

public class SmokeSensor extends EventSensor {
    public SmokeSensor() {
        super(EventType.FIRE);
    }
}
