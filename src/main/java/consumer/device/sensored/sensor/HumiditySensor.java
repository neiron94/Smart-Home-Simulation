package consumer.device.sensored.sensor;

import consumer.device.sensored.ParameterDevice;

public class HumiditySensor extends ParameterSensor {

    @Override
    public void checkInfo() {
        device.react(device.getRoom().getHumidity());
    }
}
