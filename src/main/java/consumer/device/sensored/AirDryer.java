package consumer.device.sensored;

import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.sensored.sensor.HumiditySensor;
import consumer.device.sensored.sensor.TemperatureSensor;
import place.Room;
import utils.HelpFunctions;
import utils.Percent;

public class AirDryer extends ParameterDevice<HumiditySensor> {

    public AirDryer(int id, Room startRoom, HumiditySensor sensor) {
        super(DeviceType.AIR_DRYER, id, startRoom, sensor);
    }

    @Override
    public void consumeElectricity() {
        // TODO - implement, depends on power
    }

    @Override
    public void fire() {
        // TODO - implement
    }

    @Override
    public void react(Number parameter) {
        // TODO - increment and decrement by 5?
        if (room.getHumidity() > room.getControlPanel().getHumidity())
            power = HelpFunctions.adjustPercent(++power);
        else if (room.getHumidity() < room.getControlPanel().getHumidity())
            power = HelpFunctions.adjustPercent(--power);
    }

    @Override
    public void setStatus(DeviceStatus status) {
        if (status != DeviceStatus.STANDBY)
            this.status = status;
    }
}