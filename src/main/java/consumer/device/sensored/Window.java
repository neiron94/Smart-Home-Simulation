package consumer.device.sensored;

import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.sensored.sensor.LightSensor;
import place.Room;
import utils.Percent;

import java.awt.*;

public class Window extends ParameterDevice<LightSensor> {

    public Window(int id, Room startRoom, LightSensor sensor) {
        super(DeviceType.WINDOW, id, startRoom, sensor);
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
        if (room.getBrightness().intValue() > room.getControlPanel().getBrightness().intValue())
            power.increment();
        else if (room.getBrightness().intValue() < room.getControlPanel().getBrightness().intValue())
            power.decrement();
    }

    @Override
    public void setStatus(DeviceStatus status) {
        if (status != DeviceStatus.STANDBY)
            this.status = status;
    }
}