package consumer.device.sensored;

import consumer.device.DeviceStatus;
import consumer.device.Manual;
import consumer.device.sensored.sensor.EventSensor;
import place.Room;

public abstract class EventDevice<T extends EventSensor> extends SensoredDevice<T> {

    public EventDevice(DeviceStatus startStatus, Manual manual, Room startRoom, T sensor) {
        super(startStatus, manual, startRoom, sensor);
    }

    public abstract void react();
}
