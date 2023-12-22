package consumer.device.sensored;

import consumer.device.DeviceStatus;
import consumer.device.Manual;
import consumer.device.sensored.sensor.LightSensor;
import consumer.device.sensored.sensor.TemperatureSensor;

import java.awt.*;

public class Light extends ParameterDevice<LightSensor> {

    private Percent brightness;
    private Color color;

    public Light(Room startRoom, LightSensor sensor) {
        super(DeviceStatus.ON, null, startRoom, sensor);  // TODO - manual should be taken from somewhere
        brightness.setMin();
        color = new Color(0);
    }

    @Override
    public void consumeElectricity() {
        // TODO - implement, depends on brightness
    }

    @Override
    public void fire() {
        // TODO - implement
    }

    @Override
    public void react(Number parameter) {
        // TODO - take room.getBrightness() and correct brightness
    }

    @Override
    public void setStatus(DeviceStatus status) {
        if (status != DeviceStatus.STANDBY)
            this.status = status;
    }
}
