package consumer.device.sensored;

import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.sensored.sensor.TemperatureSensor;
import place.Room;
import utils.HelpFunctions;


public class AC extends ParameterDevice<TemperatureSensor> {
    public AC(int id, Room startRoom, TemperatureSensor sensor) {
        super(DeviceType.AC, id, startRoom, sensor);
    }

    @Override
    public int consumeElectricity() {
        // TODO - implement, depends on power
        return 0;
    }

    @Override
    public void fire() {
        // TODO - implement
    }

    @Override
    public void react(Number parameter) {
        // TODO - increment and decrement by 5?
        if (room.getTemperature() > room.getControlPanel().getTemperature())
            power = HelpFunctions.adjustPercent(++power);
        else if (room.getTemperature() < room.getControlPanel().getTemperature())
            power = HelpFunctions.adjustPercent(--power);
    }

    @Override
    public void setStatus(DeviceStatus status) {
        if (status != DeviceStatus.STANDBY)
            this.status = status;
    }
}
