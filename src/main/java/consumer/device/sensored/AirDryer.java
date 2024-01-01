package consumer.device.sensored;

import consumer.device.DeviceStatus;
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
        // TODO - increment and decrement by 5?
        if (room.getHumidity() > room.getControlPanel().getHumidity())
            power = HelpFunctions.adjustPercent(++power);
        else if (room.getHumidity() < room.getControlPanel().getHumidity())
            power = HelpFunctions.adjustPercent(--power);
    }

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.AIR_DRYER * power);
    }

    @Override
    public void setStatus(DeviceStatus status) {
        if (status != DeviceStatus.STANDBY)
            this.status = status;
    }
}