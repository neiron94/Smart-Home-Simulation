package consumer.device.sensored;

import consumer.device.DeviceStatus;
import consumer.device.Manual;
import consumer.device.sensored.sensor.ParameterSensor;
import place.Room;
import utils.Percent;

public abstract class ParameterDevice<T extends ParameterSensor> extends SensoredDevice<T> {
    protected final Percent power;

    public ParameterDevice(DeviceStatus startStatus, Manual manual, Room startRoom, T sensor) {
        super(startStatus, manual, startRoom, sensor);
        this.power = new Percent(0);
    }

    public abstract void react(Number parameter);

    public Percent getPower() {
        return power;
    }
}
