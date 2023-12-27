package consumer.device.sensored;

import consumer.device.DeviceStatus;
import consumer.device.sensored.sensor.HumiditySensor;
import consumer.device.sensored.sensor.TemperatureSensor;
import place.Room;
import utils.Percent;

public class AirDryer extends ParameterDevice<HumiditySensor> {

    public AirDryer(Room startRoom, HumiditySensor sensor) {
        super(DeviceStatus.ON, null, startRoom, sensor);  // TODO - manual should be taken from somewhere
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
        if (room.getHumidity().intValue() > room.getControlPanel().getHumidity().intValue())
            power.increment();
        else if (room.getHumidity().intValue() < room.getControlPanel().getHumidity().intValue())
            power.decrement();
    }

    @Override
    public void setStatus(DeviceStatus status) {
        if (status != DeviceStatus.STANDBY)
            this.status = status;
    }
}
