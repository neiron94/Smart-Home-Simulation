package consumer.device.sensored.sensor;

import consumer.device.sensored.ParameterDevice;

public class TemperatureSensor extends ParameterSensor {

    @Override
    public void checkInfo() {
        device.react(device.getRoom().getTemperature());
    }
}
