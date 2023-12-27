package consumer.device.sensored;

import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.Manual;
import consumer.device.sensored.sensor.LightSensor;
import consumer.device.sensored.sensor.TemperatureSensor;
import place.Room;
import utils.Percent;

import java.awt.*;

public class Light extends ParameterDevice<LightSensor> {
    private Color color;

    public Light(int id, Room startRoom, LightSensor sensor) {
        super(DeviceType.LIGHT, id, startRoom, sensor);
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
        // TODO - increment and decrement by 5?
        if (room.getBrightness().intValue() < room.getControlPanel().getBrightness().intValue())
            power.increment();
        else if (room.getBrightness().intValue() > room.getControlPanel().getBrightness().intValue())
            power.decrement();

        if (color.getRGB() != room.getControlPanel().getColor().getRGB())
            color = new Color(room.getControlPanel().getColor().getRGB());
    }

    @Override
    public void setStatus(DeviceStatus status) {
        if (status != DeviceStatus.STANDBY)
            this.status = status;
    }

    public Color getColor() {
        return color;
    }
}
