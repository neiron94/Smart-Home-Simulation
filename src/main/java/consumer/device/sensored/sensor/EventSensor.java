package consumer.device.sensored.sensor;

import consumer.device.sensored.EventDevice;
import event.EventType;

public abstract class EventSensor extends Sensor<EventDevice<?>> {

    protected final EventType eventType;

    protected EventSensor(EventDevice<?> device, EventType eventType) {
        super(device);
        this.eventType = eventType;
    }

    @Override
    public void checkInfo() {
        // TODO - look for a eventType event in this room, if exists -> device.react()
    }
}
