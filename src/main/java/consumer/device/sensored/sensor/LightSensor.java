package consumer.device.sensored.sensor;

import consumer.device.sensored.ParameterDevice;

public class LightSensor extends ParameterSensor {

    @Override
    public void checkInfo() {
        device.react(device.getRoom().getBrightness());
    }
}
