package consumer.device.common;

import java.time.LocalTime;

public enum WasherProgram {
    DELICATE(LocalTime.of(0,0), 0, 0),  // TODO - set values
    NORMAL(LocalTime.of(0,0), 0, 0),
    INTENSIVE(LocalTime.of(0,0), 0, 0);

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
