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
        // TODO - increment and decrement by 5?
        if (room.getBrightness() > room.getControlPanel().getBrightness())
            power = HelpFunctions.adjustPercent(++power);
        else if (room.getBrightness() < room.getControlPanel().getBrightness())
            power = HelpFunctions.adjustPercent(--power);
    }

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.WINDOW * power);
    }

    @Override
    public void setStatus(DeviceStatus status) {
        if (status != DeviceStatus.STANDBY)
            this.status = status;
    }
}