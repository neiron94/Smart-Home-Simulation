package consumer.device.common;

import java.time.LocalTime;

public enum DishwasherProgram {
    LIGHT(LocalTime.of(0,0), 0, 0), // TODO - set values
    MEDIUM(LocalTime.of(0,0), 0, 0),
    HEAVY(LocalTime.of(0,0), 0, 0);

    private final LocalTime duration;
    private final double electricityConsumption;
    private final double waterConsumption;  // liters per hour

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
