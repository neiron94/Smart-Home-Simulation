package consumer.device.common;

import utils.Constants.WorkTime;
import utils.Constants.Consumption.Electricity;

import java.time.Duration;
import java.time.LocalTime;

public enum ToastType {
    SANDWICH("Sandwich", WorkTime.TOASTER_SANDWICH, Electricity.TOASTER_SANDWICH),
    GOLDEN("Golden", WorkTime.TOASTER_GOLDEN, Electricity.TOASTER_GOLDEN),
    CRUST("Crust", WorkTime.TOASTER_CRUST, Electricity.TOASTER_CRUST);

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
