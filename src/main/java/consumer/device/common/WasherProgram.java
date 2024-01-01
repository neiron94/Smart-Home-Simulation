package consumer.device.common;

import utils.Constants.WorkTime;
import utils.Constants.Consumption.Electricity;
import utils.Constants.Consumption.Water;

import java.time.LocalTime;

public enum WasherProgram {
    DELICATE(WorkTime.WASHER_DELICATE, Electricity.WASHER_DELICATE, Water.WASHER_DELICATE),
    NORMAL(WorkTime.WASHER_NORMAL, Electricity.WASHER_NORMAL, Water.WASHER_NORMAL),
    INTENSIVE(WorkTime.WASHER_INTENSIVE, Electricity.WASHER_INTENSIVE, Water.WASHER_INTENSIVE);

    private final LocalTime duration;
    private final double power;
    private final double waterConsumption;

    WasherProgram(LocalTime duration, double power, double waterConsumption) {
        this.duration = duration;
        this.power = power;
        this.waterConsumption = waterConsumption;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public double getPower() {
        return power;
    }

    public double getWaterConsumption() {
        return waterConsumption;
    }
}
