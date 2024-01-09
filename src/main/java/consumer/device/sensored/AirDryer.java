package consumer.device.sensored;

import consumer.device.DeviceType;
import consumer.device.sensored.sensor.HumiditySensor;;
import place.Room;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;

public class AirDryer extends ParameterDevice<HumiditySensor> {
    public AirDryer(int id, Room startRoom) {
        super(DeviceType.AIR_DRYER, id, startRoom);
        this.sensor = new HumiditySensor();
        this.sensor.attachDevice(this);
    }

    @Override
    public void react(Number parameter) {
        if (parameter.doubleValue() > room.getControlPanel().getHumidity())
            setPower(power + 1);
        else if (parameter.doubleValue() < room.getControlPanel().getHumidity())
            setPower(power - 1);
    }

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.AIR_DRYER * power);
    }

    @Override
    public AirDryer copy() {
        return new AirDryer(id, room);
    }
}