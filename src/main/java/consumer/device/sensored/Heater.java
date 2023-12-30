package consumer.device.sensored;

import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.sensored.sensor.GasSensor;
import consumer.device.sensored.sensor.TemperatureSensor;
import place.Room;
import utils.HelpFunctions;


public class Heater extends ParameterDevice<TemperatureSensor> {

    public Heater(int id, Room startRoom) {
        super(DeviceType.HEATER, id, startRoom);
        this.sensor = new TemperatureSensor();
        this.sensor.attachDevice(this);
    }

    @Override
    public void react(Number parameter) {
        // TODO - increment and decrement by 5?
        if (room.getTemperature() < room.getControlPanel().getTemperature())
            power = HelpFunctions.adjustPercent(++power);
        else if (room.getTemperature() > room.getControlPanel().getTemperature())
            power = HelpFunctions.adjustPercent(--power);
    }

    @Override
    public void setStatus(DeviceStatus status) {
        if (status != DeviceStatus.STANDBY)
            this.status = status;
    }
}