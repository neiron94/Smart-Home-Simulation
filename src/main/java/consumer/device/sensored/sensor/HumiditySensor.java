package consumer.device.sensored.sensor;

import consumer.device.sensored.ParameterDevice;

public class HumiditySensor extends ParameterSensor {
    protected HumiditySensor(ParameterDevice<?> device) {
        super(device);
    }

    @Override
    public void checkInfo() {
        device.react(device.getRoom().getHumidity());
    }
}
