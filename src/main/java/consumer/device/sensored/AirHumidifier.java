package consumer.device.sensored;

import consumer.device.DeviceType;
import consumer.device.sensored.sensor.HumiditySensor;
import place.Room;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;

/**
 * AirHumidifier for increasing room's humidity.
 */
public class AirHumidifier extends ParameterDevice<HumiditySensor> {
    public AirHumidifier(int id, Room startRoom) {
        super(DeviceType.AIR_HUMIDIFIER, id, startRoom);
        this.sensor = new HumiditySensor();
        this.sensor.attachDevice(this);
    }

    /**
     * Increase or decrease power depending on given parameter ond preferred parameter
     * in Control Panel of the room.
     * @param parameter Changed room parameter.
     */
    @Override
    public void react(Number parameter) {
        if (parameter.doubleValue() < room.getControlPanel().getHumidity())
            setPower(power + 1);
        else if (parameter.doubleValue() > room.getControlPanel().getHumidity())
            setPower(power - 1);
    }

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.AIR_HUMIDIFIER * power / 100);
    }

    @Override
    public AirHumidifier copy() {
        return new AirHumidifier(id, room);
    }
}