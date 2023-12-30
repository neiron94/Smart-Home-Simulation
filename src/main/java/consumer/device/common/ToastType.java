package consumer.device.common;

import java.time.LocalTime;

public enum ToastType {
    SANDWICH(LocalTime.of(0,0), 0), // TODO - set values
    GOLDEN(LocalTime.of(0,0), 0),
    CRUST(LocalTime.of(0,0), 0);

    private final LocalTime cookTime;
    private final double power;


    ToastType(LocalTime cookTime, double power) {
        this.cookTime = cookTime;
        this.power = power;
    }

    public LocalTime getCookTime() {
        return cookTime;
    }

    public double getPower() {
        return power;
    }
}
