package consumer.device.sensored;

import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.Manual;
import consumer.device.sensored.sensor.ParameterSensor;
import place.Room;
import utils.HelpFunctions;


public abstract class ParameterDevice<T extends ParameterSensor> extends SensoredDevice<T> {
    protected int power;    // percent

    public ParameterDevice(DeviceType type, int id, Room startRoom) {
        super(type, id, startRoom);
        this.power = 0;
    }

    public abstract void react(Number parameter);

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = HelpFunctions.adjustPercent(power);
    }

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, 1.0 * power);  // TODO - change 1.0 for Constant (electricity consumption with max power)
    }
}
