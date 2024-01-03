package consumer.device.common;

import utils.Constants.WorkTime;
import utils.Constants.Consumption.Electricity;

import java.time.Duration;
import java.time.LocalTime;

public enum DryerProgram {
    COLD("Cold", WorkTime.DRYER_COLD, Electricity.DRYER_COLD),
    NORMAL("Normal", WorkTime.DRYER_NORMAL, Electricity.DRYER_NORMAL),
    HOT("Hot", WorkTime.DRYER_HOT, Electricity.DRYER_HOT);

    private final String name;
    private final Duration duration;
    private final double electricityConsumption;

    DryerProgram(String name, Duration duration, double electricityConsumption) {
        this.name = name;
        this.duration = duration;
        this.electricityConsumption = electricityConsumption;
    }

    public Duration getDuration() {
        return duration;
    }

    public double getElectricityConsumption() {
        return electricityConsumption;
    }

    public String toString() {
        return String.format("Dryer %s program", name);
    }
}
