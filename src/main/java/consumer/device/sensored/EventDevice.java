package consumer.device.sensored;

import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.Manual;
import consumer.device.sensored.sensor.EventSensor;
import place.Room;

public abstract class EventDevice<T extends EventSensor> extends SensoredDevice<T> {

    public EventDevice(DeviceType type, int id, Room startRoom, T sensor) {
        super(type, id, startRoom, sensor);
    }

    public abstract void react();
}