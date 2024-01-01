package consumer.device.common;

import utils.Constants.WorkTime;
import utils.Constants.Consumption.Electricity;

import java.time.LocalTime;

public enum DryerProgram {
    COLD(WorkTime.DRYER_COLD, Electricity.DRYER_COLD),
    NORMAL(WorkTime.DRYER_NORMAL, Electricity.DRYER_NORMAL),
    HOT(WorkTime.DRYER_HOT, Electricity.DRYER_HOT);

    private final LocalTime duration;
    private final double electricityConsumption;

    DryerProgram(LocalTime duration, double electricityConsumption) {
        this.duration = duration;
        this.electricityConsumption = electricityConsumption;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public double getElectricityConsumption() {
        return electricityConsumption;
    }
}
