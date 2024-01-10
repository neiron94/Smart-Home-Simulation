package consumer.device.common;

import utils.Constants.WorkTime;
import utils.Constants.Consumption.Electricity;
import utils.Constants.Consumption.Water;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Random;

/**
 * Programs for dishwasher. Stores information about programs' duration and consumption.
 */
public enum DishwasherProgram {
    LIGHT("Light", WorkTime.DISHWASHER_LIGHT, Electricity.DISHWASHER_LIGHT, Water.DISHWASHER_LIGHT),
    MEDIUM("Medium", WorkTime.DISHWASHER_MEDIUM, Electricity.DISHWASHER_MEDIUM, Water.DISHWASHER_MEDIUM),
    HEAVY("Heavy", WorkTime.DISHWASHER_HEAVY, Electricity.DISHWASHER_HEAVY, Water.DISHWASHER_HEAVY);

    private final String name;
    private final Duration duration;
    private final double electricityConsumption;
    private final double waterConsumption;

    DishwasherProgram(String name, Duration duration, double electricityConsumption, double waterConsumption) {
        this.name = name;
        this.duration = duration;
        this.electricityConsumption = electricityConsumption;
        this.waterConsumption = waterConsumption;
    }

    /**
     * Take random dishwasher program.
     * @return Random dishwasher program.
     */
    public static DishwasherProgram getRandomProgram() {
        return DishwasherProgram.values()[new Random().nextInt(0, DishwasherProgram.values().length)];
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

    public String toString() {
        return String.format("Dishwasher %s program", name);
    }
}
