package consumer.device.sensored;

import consumer.device.Device;
import consumer.ElectricityConsumer;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.Manual;
import consumer.device.sensored.sensor.Sensor;
import place.Room;

public abstract class SensoredDevice<T extends Sensor<?>> extends Device implements ElectricityConsumer {

    protected final T sensor;

    public SensoredDevice(DeviceType type, int id, Room startRoom, T sensor) {
        super(type, id, startRoom);
        this.sensor = sensor;
    }

    @Override
    public void routine() {
        super.routine();
        sensor.checkInfo();
    }
}