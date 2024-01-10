package consumer.device.sensored.sensor;

import consumer.device.sensored.ParameterDevice;

/**
 * Sensor for checking humidity of the room.
 */
public class HumiditySensor extends ParameterSensor {

    /**
     * Check humidity of the room and send it to attached device.
     */
    @Override
    public void checkInfo() {
        device.react(device.getRoom().getHumidity());
    }
}
