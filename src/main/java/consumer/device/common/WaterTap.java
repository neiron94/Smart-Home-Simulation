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
import utils.exceptions.DeviceIsBrokenException;
import utils.exceptions.ResourceNotAvailableException;


public class WaterTap extends Device implements WaterConsumer, ElectricityConsumer {
    private static final double MAX_TEMPERATURE = 60;
    private static final double MIN_TEMPERATURE = 10;

    private double temperature; // 10-60 °C
    private int openness;   // percent

    public WaterTap(int id, Room startRoom) {
        super(DeviceType.WATER_TAP, id, startRoom);
        setOpenness(0);
        setTemperature(0);
    }

    //--------- Main public functions ----------//

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.WATER_TAP * temperature / MAX_TEMPERATURE);
    }

    @Override
    public double consumeWater() {
        return HelpFunctions.countWaterConsumption(status, Water.WATER_TAP * openness / 100);
    }

    //---------- API for human -----------//

    public void open(int temperature, int openness) throws DeviceIsBrokenException, ResourceNotAvailableException {
        checkBeforeStatusSet();

        setTemperature(temperature);
        setOpenness(openness);
        this.status = DeviceStatus.ON;
    }

    public void close() {
        openness = 0;
        this.status = DeviceStatus.OFF;
    }

    //---------- Getters and Setters ----------//

    public double getTemperature() {
        return temperature;
    }

    private void setTemperature(double temperature) {
        this.temperature = HelpFunctions.adjustToRange(temperature, MIN_TEMPERATURE, MAX_TEMPERATURE);
    }

    public int getOpenness() {
        return openness;
    }

    private void setOpenness(int openness) {
        this.openness = HelpFunctions.adjustPercent(openness);
    }
}
