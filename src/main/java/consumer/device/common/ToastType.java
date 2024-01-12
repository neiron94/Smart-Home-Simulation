package consumer.device.common;

import utils.Constants.WorkDuration;
import utils.Constants.Consumption.Electricity;

import java.time.Duration;

/**
 * Types of toast. Stores information about duration of cooking and consumption.
 */
public enum ToastType {
    SANDWICH("Sandwich", WorkDuration.TOASTER_SANDWICH, Electricity.TOASTER_SANDWICH),
    GOLDEN("Golden", WorkDuration.TOASTER_GOLDEN, Electricity.TOASTER_GOLDEN),
    CRUST("Crust", WorkDuration.TOASTER_CRUST, Electricity.TOASTER_CRUST);

    private final String name;
    private final Duration cookTime;
    private final double power;


    ToastType(String name, Duration cookTime, double power) {
        this.name = name;
        this.cookTime = cookTime;
        this.power = power;
    }

    public Duration getCookTime() {
        return cookTime;
    }

    public double getPower() {
        return power;
    }

    public String toString() {
        return String.format("%s toast", name);
    }
}
