package consumer.device.sensored;

import consumer.device.Device;
import consumer.ElectricityConsumer;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.Manual;
import consumer.device.sensored.sensor.Sensor;
import place.Room;

public abstract class SensoredDevice<T extends Sensor<?>> extends Device implements ElectricityConsumer {

    protected T sensor;

    public SensoredDevice(DeviceType type, int id, Room startRoom) {
        super(type, id, startRoom);
    }

    @Override
    public boolean routine() {
        if (!super.routine()) return false;

        sensor.checkInfo();

        return true;
    }
}