package consumer.device.common;

import utils.Constants.Consumption.Electricity;

public enum VentProgram {
    SLOW(Electricity.VENT_SLOW),
    NORMAL(Electricity.VENT_NORMAL),
    TURBO(Electricity.VENT_TURBO);

    private final double power;


    VentProgram(double power) {
        this.power = power;
    }

    public double getPower() {
        return power;
    }
}
