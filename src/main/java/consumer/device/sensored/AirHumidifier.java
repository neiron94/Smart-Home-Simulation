package consumer.device.sensored;

import consumer.device.DeviceType;
import consumer.device.sensored.sensor.HumiditySensor;
import place.Room;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;

public class AirHumidifier extends ParameterDevice<HumiditySensor> {
    public AirHumidifier(int id, Room startRoom) {
        super(DeviceType.AIR_HUMIDIFIER, id, startRoom);
        this.sensor = new HumiditySensor();
        this.sensor.attachDevice(this);
    }

    @Override
    public void react(Number parameter) {
        if (parameter.doubleValue() < room.getControlPanel().getHumidity())
            setPower(power + 1);
        else if (parameter.doubleValue() > room.getControlPanel().getHumidity())
            setPower(power - 1);
    }

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.AIR_HUMIDIFIER * power);
    }

    @Override
    public AirHumidifier copy() {
        return new AirHumidifier(id, room);
    }
}