package consumer.device.common;

import utils.Constants.WorkTime;
import utils.Constants.Consumption.Electricity;

import java.time.LocalTime;

public enum ToastType {
    SANDWICH(WorkTime.TOASTER_SANDWICH, Electricity.TOASTER_SANDWICH),
    GOLDEN(WorkTime.TOASTER_GOLDEN, Electricity.TOASTER_GOLDEN),
    CRUST(WorkTime.TOASTER_CRUST, Electricity.TOASTER_CRUST);

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
