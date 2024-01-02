package consumer.device.common;

import utils.Constants.WorkTime;
import utils.Constants.Consumption.Electricity;
import utils.Constants.Consumption.Water;

import java.time.Duration;
import java.time.LocalTime;

public enum WasherProgram {
    DELICATE("Delicate", WorkTime.WASHER_DELICATE, Electricity.WASHER_DELICATE, Water.WASHER_DELICATE),
    NORMAL("Normal", WorkTime.WASHER_NORMAL, Electricity.WASHER_NORMAL, Water.WASHER_NORMAL),
    INTENSIVE("Intensive", WorkTime.WASHER_INTENSIVE, Electricity.WASHER_INTENSIVE, Water.WASHER_INTENSIVE);

    private final String description;
    private final Duration duration;
    private final double power;
    private final double waterConsumption;

    WasherProgram(String description, Duration duration, double power, double waterConsumption) {
        this.description = description;
        this.duration = duration;
        this.power = power;
        this.waterConsumption = waterConsumption;
    }

    public String getDescription() {
        return description;
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
}
