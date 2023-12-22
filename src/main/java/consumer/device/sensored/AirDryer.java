package consumer.device.sensored;

import consumer.device.DeviceStatus;
import consumer.device.sensored.sensor.HumiditySensor;
import consumer.device.sensored.sensor.TemperatureSensor;

public class AirDryer extends ParameterDevice<HumiditySensor> {

    private Percent power;

    public AirDryer(Room startRoom, HumiditySensor sensor) {
        super(DeviceStatus.ON, null, startRoom, sensor);  // TODO - manual should be taken from somewhere
        power.setMin();
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
        // TODO - take room.getHumidity() and correct power
    }

    @Override
    public void setStatus(DeviceStatus status) {
        if (status != DeviceStatus.STANDBY)
            this.status = status;
    }
}
