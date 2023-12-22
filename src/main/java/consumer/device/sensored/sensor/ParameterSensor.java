package consumer.device.sensored.sensor;

import consumer.device.sensored.ParameterDevice;

public abstract class ParameterSensor extends Sensor<ParameterDevice<?>> {
    protected ParameterSensor(ParameterDevice<?> device) {
        super(device);
    }
}
