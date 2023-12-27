package consumer.device.sensored;

import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.sensored.sensor.HumiditySensor;
import place.Room;
import utils.Percent;

public class AirHumidifier extends ParameterDevice<HumiditySensor> {

    public AirHumidifier(int id, Room startRoom, HumiditySensor sensor) {
        super(DeviceType.AIR_HUMIDIFIER, id, startRoom, sensor);
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
        if (room.getHumidity().intValue() < room.getControlPanel().getHumidity().intValue())
            power.increment();
        else if (room.getHumidity().intValue() > room.getControlPanel().getHumidity().intValue())
            power.decrement();
    }

    @Override
    public void setStatus(DeviceStatus status) {
        if (status != DeviceStatus.STANDBY)
            this.status = status;
    }
}