package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.WaterConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import place.Room;
import utils.Constants.Consumption.Water;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;


public class WaterTap extends Device implements WaterConsumer, ElectricityConsumer {
    private static final double MAX_TEMPERATURE = 60;
    private static final double MIN_TEMPERATURE = 10;

    private double temperature; // 10-60 °C
    private int openness;   // percent

    public WaterTap(int id, Room startRoom) {
        super(DeviceType.WATER_TAP, id, startRoom);
        // TODO - set openness, temperature?
    }

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.WATER_TAP * temperature / MAX_TEMPERATURE);
    }

    @Override
    public double consumeWater() {
        return HelpFunctions.countWaterConsumption(status, Water.WATER_TAP * openness / 100);
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
        this.temperature = HelpFunctions.adjustToRange(temperature, MIN_TEMPERATURE, MAX_TEMPERATURE);
    }

    public int getOpenness() {
        return openness;
    }

    public void setOpenness(int openness) {
        this.openness = HelpFunctions.adjustPercent(openness);
    }
}
