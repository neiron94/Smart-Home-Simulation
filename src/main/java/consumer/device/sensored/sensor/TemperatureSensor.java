package consumer.device.sensored.sensor;

import consumer.device.sensored.ParameterDevice;

public class TemperatureSensor extends ParameterSensor {
    protected TemperatureSensor(ParameterDevice<?> device) {
        super(device);
    }

    @Override
    public void checkInfo() {
        device.react(device.getRoom().getTemperature());
    }
}
