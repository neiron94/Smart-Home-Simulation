package consumer.device.sensored;

import consumer.device.DeviceStatus;
import consumer.device.sensored.sensor.LightSensor;

import java.awt.*;

public class Window extends ParameterDevice<LightSensor> {

    private Percent opacity;

    public Window(Room startRoom, LightSensor sensor) {
        super(DeviceStatus.ON, null, startRoom, sensor);  // TODO - manual should be taken from somewhere
        opacity.setMin();
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
        // TODO - take room.getBrightness(), streetBrightness and correct opacity
    }

    @Override
    public void setStatus(DeviceStatus status) {
        if (status != DeviceStatus.STANDBY)
            this.status = status;
    }
}
