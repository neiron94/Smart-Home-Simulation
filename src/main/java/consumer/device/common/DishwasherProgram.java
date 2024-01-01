package consumer.device.common;

import utils.Constants.WorkTime;
import utils.Constants.Consumption.Electricity;
import utils.Constants.Consumption.Water;
import java.time.LocalTime;

public enum DishwasherProgram {
    LIGHT(WorkTime.DISHWASHER_LIGHT, Electricity.DISHWASHER_LIGHT, Water.DISHWASHER_LIGHT),
    MEDIUM(WorkTime.DISHWASHER_MEDIUM, Electricity.DISHWASHER_MEDIUM, Water.DISHWASHER_MEDIUM),
    HEAVY(WorkTime.DISHWASHER_HEAVY, Electricity.DISHWASHER_HEAVY, Water.DISHWASHER_HEAVY);

    private final LocalTime duration;
    private final double electricityConsumption;
    private final double waterConsumption;

    DishwasherProgram(LocalTime duration, double electricityConsumption, double waterConsumption) {
        this.duration = duration;
        this.electricityConsumption = electricityConsumption;
        this.waterConsumption = waterConsumption;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public double getElectricityConsumption() {
        return electricityConsumption;
    }

    public double getWaterConsumption() {
        return waterConsumption;
    }
}
