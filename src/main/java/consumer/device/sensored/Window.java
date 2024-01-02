package consumer.device.sensored;

import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.sensored.sensor.LightSensor;
import place.Room;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;

public class Window extends ParameterDevice<LightSensor> {

    public Window(int id, Room startRoom) {
        super(DeviceType.WINDOW, id, startRoom);
        this.sensor = new LightSensor();
        this.sensor.attachDevice(this);
    }

    @Override
    public void react(Number parameter) {
        if (room.getBrightness() > room.getControlPanel().getBrightness())
            setPower(power + 1);
        else if (room.getBrightness() < room.getControlPanel().getBrightness())
            setPower(power - 1);
    }

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.WINDOW * power);
    }
}