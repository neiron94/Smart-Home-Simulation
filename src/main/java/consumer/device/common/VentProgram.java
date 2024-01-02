package consumer.device.common;

import utils.Constants.Consumption.Electricity;

public enum VentProgram {
    SLOW("Slow", Electricity.VENT_SLOW),
    NORMAL("Normal", Electricity.VENT_NORMAL),
    TURBO("Turbo", Electricity.VENT_TURBO);

    private final String description;
    private final double power;


    VentProgram(String description, double power) {
        this.description = description;
        this.power = power;
    }

    public String getDescription() {
        return description;
    }

    public double getPower() {
        return power;
    }
}
