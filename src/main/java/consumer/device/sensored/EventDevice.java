package consumer.device.sensored;

import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.Manual;
import consumer.device.sensored.sensor.EventSensor;
import place.Room;

/**
 * Sensored device which works with events.
 * @param <T> sensor, which works with events.
 */
public abstract class EventDevice<T extends EventSensor> extends SensoredDevice<T> {

    public EventDevice(DeviceType type, int id, Room startRoom) {
        super(type, id, startRoom);
    }

    /**
     * React to event.
     */
    public abstract void react();
}