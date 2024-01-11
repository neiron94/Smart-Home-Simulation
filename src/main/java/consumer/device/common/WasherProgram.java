package consumer.device.common;

import utils.Constants.WorkTime;
import utils.Constants.Consumption.Electricity;
import utils.Constants.Consumption.Water;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Random;

/**
 * Washer programs. Stores information about programs' duration and consumption.
 */
public enum WasherProgram {
    DELICATE("Delicate", WorkTime.WASHER_DELICATE, Electricity.WASHER_DELICATE, Water.WASHER_DELICATE),
    NORMAL("Normal", WorkTime.WASHER_NORMAL, Electricity.WASHER_NORMAL, Water.WASHER_NORMAL),
    INTENSIVE("Intensive", WorkTime.WASHER_INTENSIVE, Electricity.WASHER_INTENSIVE, Water.WASHER_INTENSIVE);

    private final String name;
    private final Duration duration;
    private final double power;
    private final double waterConsumption;

    WasherProgram(String name, Duration duration, double power, double waterConsumption) {
        this.name = name;
        this.duration = duration;
        this.power = power;
        this.waterConsumption = waterConsumption;
    }

    public Duration getDuration() {
        return duration;
    }

    public double getPower() {
        return power;
    }

    public double getWaterConsumption() {
        return waterConsumption;
    }

    public String toString() {
        return String.format("Washer %s program", name);
    }
}
