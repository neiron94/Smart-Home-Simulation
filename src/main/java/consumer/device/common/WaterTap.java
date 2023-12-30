package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.WaterConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import place.Room;
import utils.HelpFunctions;


public class WaterTap extends Device implements WaterConsumer, ElectricityConsumer {

    private double temperature;
    private int openness;   // percent

    public WaterTap(int id, Room startRoom) {
        super(DeviceType.WATER_TAP, id, startRoom);
        // TODO - set openness, temperature?
    }

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, temperature / 2);  // TODO - change 2 for Constant (temperature for 1kW)
    }

    @Override
    public double consumeWater() {
        return HelpFunctions.countWaterConsumption(status, 1.0 * openness); // TODO - change 1.0 for Constant (water consumption when openness is max)
    }

    public void open(int temperature, int openness) {
        // TODO - check durability
        this.temperature = temperature;
        this.openness = HelpFunctions.adjustPercent(openness);
        this.status = DeviceStatus.ON;
        // TODO - smth else?
    }

    public void close() {
        this.status = DeviceStatus.OFF;
        openness = 0;
        // TODO - smth else?
    }

    @Override
    public void setStatus(DeviceStatus status) {
        if (status != DeviceStatus.STANDBY)
            this.status = status;
    }

    // TODO - maybe delete some getters or setters

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getOpenness() {
        return openness;
    }

    public void setOpenness(int openness) {
        this.openness = HelpFunctions.adjustPercent(openness);
    }
}
