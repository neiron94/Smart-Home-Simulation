package consumer.device.sensored;

import consumer.device.Device;
import consumer.ElectricityConsumer;
import consumer.device.DeviceStatus;
import consumer.device.Manual;
import consumer.device.sensored.sensor.Sensor;

public abstract class SensoredDevice<T extends Sensor<?>> extends Device implements ElectricityConsumer {

    protected final T sensor;

    public SensoredDevice(DeviceStatus startStatus, Manual manual, Room startRoom, T sensor) {
        super(startStatus, manual, startRoom);
        this.sensor = sensor;
    }

    @Override
    public void routine() {
        super.routine();
        sensor.checkInfo();
    }
}
