package consumer.device.sensored;

import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.sensored.sensor.TemperatureSensor;
import place.Room;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;


public class AC extends ParameterDevice<TemperatureSensor> {
    public AC(int id, Room startRoom) {
        super(DeviceType.AC, id, startRoom);
        this.sensor = new TemperatureSensor();
        this.sensor.attachDevice(this);
    }

    @Override
    public void react(Number parameter) {
        if (parameter.doubleValue() > room.getControlPanel().getTemperature())
            setPower(power + 1);
        else if (parameter.doubleValue() < room.getControlPanel().getTemperature())
            setPower(power - 1);
    }

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.AC * power / 100);
    }
}
