package consumer.device.sensored;

import consumer.device.Device;
import consumer.ElectricityConsumer;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.Manual;
import consumer.device.sensored.sensor.Sensor;
import place.Room;

/**
 * Device which has sensor attached and gets information from it.
 * @param <T> Type of sensor.
 */
public abstract class SensoredDevice<T extends Sensor<?>> extends Device implements ElectricityConsumer {

    protected T sensor;

    public SensoredDevice(DeviceType type, int id, Room startRoom) {
        super(type, id, startRoom);
    }

    /**
     * Sensor check information every tick.
     * @return Can be ignored.
     */
    @Override
    public boolean routine() {
        if (!super.routine()) return false;

        sensor.checkInfo();

        return true;
    }
}