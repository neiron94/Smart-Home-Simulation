package consumer.device.common;

public enum VentProgram {
    SLOW(0),    // TODO - set values
    NORMAL(0),
    TURBO(0);

    private final double power;


    VentProgram(double power) {
        this.power = power;
    }

    public double getPower() {
        return power;
    }
}
