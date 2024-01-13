package consumer.device.sensored.sensor;

/**
 * Sensor for checking brightness of the room.
 */
public class LightSensor extends ParameterSensor {

    /**
     * Check brightness of the room and send it to attached device.
     */
    @Override
    public void checkInfo() {
        device.react(device.getRoom().getBrightness());
    }
}
