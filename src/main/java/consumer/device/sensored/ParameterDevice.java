package consumer.device.sensored;

import consumer.device.DeviceStatus;
import consumer.device.Manual;
import consumer.device.sensored.sensor.ParameterSensor;
import place.Room;

public abstract class ParameterDevice<T extends ParameterSensor> extends SensoredDevice<T> {
    public ParameterDevice(DeviceStatus startStatus, Manual manual, Room startRoom, T sensor) {
        super(startStatus, manual, startRoom, sensor);
    }

    public abstract void react(Number parameter);
}
