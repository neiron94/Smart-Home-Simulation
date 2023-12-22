package consumer.device.sensored.sensor;

import consumer.device.sensored.SensoredDevice;

public abstract class Sensor<T extends SensoredDevice<?>> {
    protected final T device;

    protected Sensor(T device) {
        this.device = device;
    }

    public abstract void checkInfo();
}
