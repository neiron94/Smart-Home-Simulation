package consumer.device.sensored;

import consumer.device.DeviceStatus;
import consumer.device.Manual;
import consumer.device.sensored.sensor.TemperatureSensor;

public class AC extends ParameterDevice<TemperatureSensor> {

    private Percent power;

    public AC(Room startRoom, TemperatureSensor sensor) {
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
        // TODO - take room.getTemperature() and correct power
    }

    @Override
    public void setStatus(DeviceStatus status) {
        if (status != DeviceStatus.STANDBY)
            this.status = status;
    }
}
