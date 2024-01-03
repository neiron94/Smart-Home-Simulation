package consumer.device.common;

import utils.Constants.Consumption.Electricity;

public enum VentProgram {
    SLOW("Slow", Electricity.VENT_SLOW),
    NORMAL("Normal", Electricity.VENT_NORMAL),
    TURBO("Turbo", Electricity.VENT_TURBO);

    private final String name;
    private final double power;


    VentProgram(String name, double power) {
        this.name = name;
        this.power = power;
    }

    public double getPower() {
        return power;
    }

    public String toString() {
        return String.format("Vent %s program", name);
    }
}
