package consumer.device.common;

import java.time.LocalTime;

public enum DryerProgram {
    COLD(LocalTime.of(0,0), 0), // TODO - set values
    NORMAL(LocalTime.of(0,0), 0),
    HOT(LocalTime.of(0,0), 0);

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
