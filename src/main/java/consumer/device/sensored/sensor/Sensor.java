package consumer.device.sensored.sensor;

import consumer.device.sensored.SensoredDevice;

public abstract class Sensor<T extends SensoredDevice<?>> {
    protected T device;

    public abstract void checkInfo();

    public void attachDevice(T device) {
        if (this.device == null)
            this.device = device;
        else
            throw new IllegalArgumentException("Device is already attached");   // TODO - throw different exception?
    }
}
