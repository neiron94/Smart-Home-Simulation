package consumer.device.common;

import utils.Constants.WorkTime;
import utils.Constants.Consumption.Electricity;
import utils.Constants.Consumption.Water;

import java.time.Duration;
import java.time.LocalTime;

public enum DishwasherProgram {
    LIGHT("Light", WorkTime.DISHWASHER_LIGHT, Electricity.DISHWASHER_LIGHT, Water.DISHWASHER_LIGHT),
    MEDIUM("Medium", WorkTime.DISHWASHER_MEDIUM, Electricity.DISHWASHER_MEDIUM, Water.DISHWASHER_MEDIUM),
    HEAVY("Heavy", WorkTime.DISHWASHER_HEAVY, Electricity.DISHWASHER_HEAVY, Water.DISHWASHER_HEAVY);

    private final String description;
    private final Duration duration;
    private final double electricityConsumption;
    private final double waterConsumption;

    DishwasherProgram(String description, Duration duration, double electricityConsumption, double waterConsumption) {
        this.description = description;
        this.duration = duration;
        this.electricityConsumption = electricityConsumption;
        this.waterConsumption = waterConsumption;
    }

    public String getDescription() {
        return description;
    }

    public Duration getDuration() {
        return duration;
    }

    public double getElectricityConsumption() {
        return electricityConsumption;
    }

    public double getWaterConsumption() {
        return waterConsumption;
    }
}
