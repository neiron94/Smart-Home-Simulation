package consumer.device.sensored.sensor;

import consumer.device.sensored.ParameterDevice;

/**
 * Sensor for checking temperature of the room.
 */
public class TemperatureSensor extends ParameterSensor {

    /**
     * Check temperature of the room and send it to attached device.
     */
    @Override
    public void checkInfo() {
        device.react(device.getRoom().getTemperature());
    }
}
