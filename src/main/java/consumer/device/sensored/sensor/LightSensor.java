package consumer.device.sensored.sensor;

import consumer.device.sensored.ParameterDevice;

public class LightSensor extends ParameterSensor {
    protected LightSensor(ParameterDevice<?> device) {
        super(device);
    }

    @Override
    public void checkInfo() {
        // TODO - room.getBrightness() -> device.react(brightness)
    }
}
