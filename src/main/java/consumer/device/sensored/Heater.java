package consumer.device.sensored;

import consumer.device.DeviceType;
import consumer.device.sensored.sensor.TemperatureSensor;
import place.Room;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;

/**
 * Heater for increasing room's temperature.
 */
public class Heater extends ParameterDevice<TemperatureSensor> {

    public Heater(int id, Room startRoom) {
        super(DeviceType.HEATER, id, startRoom);
        this.sensor = new TemperatureSensor();
        this.sensor.attachDevice(this);
    }

    /**
     * Increase or decrease power depending on given parameter ond preferred parameter
     * in Control Panel of the room.
     * @param parameter Changed room parameter.
     */
    @Override
    public void react(Number parameter) {
        if (parameter.doubleValue() < room.getControlPanel().getTemperature())
            setPower(power + 1);
        else if (parameter.doubleValue() > room.getControlPanel().getTemperature())
            setPower(power - 1);
    }

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.HEATER * power / 100);
    }

    @Override
    public Heater copy() {
        return new Heater(id, room);
    }
}