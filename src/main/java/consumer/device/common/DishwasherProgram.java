/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer.device.common;

import utils.Constants.WorkDuration;
import utils.Constants.Consumption.Electricity;
import utils.Constants.Consumption.Water;
import java.time.Duration;

/**
 * Programs for dishwasher. Stores information about programs' duration and consumption.
 */
public enum DishwasherProgram {
    LIGHT("Light", WorkDuration.DISHWASHER_LIGHT, Electricity.DISHWASHER_LIGHT, Water.DISHWASHER_LIGHT),
    MEDIUM("Medium", WorkDuration.DISHWASHER_MEDIUM, Electricity.DISHWASHER_MEDIUM, Water.DISHWASHER_MEDIUM),
    HEAVY("Heavy", WorkDuration.DISHWASHER_HEAVY, Electricity.DISHWASHER_HEAVY, Water.DISHWASHER_HEAVY);

    private final String name;
    private final Duration duration;
    private final double electricityConsumption;
    private final double waterConsumption;

    DishwasherProgram(String name, Duration duration, double electricityConsumption, double waterConsumption) {
        this.name = name;
        this.duration = duration;
        this.electricityConsumption = electricityConsumption;
        this.waterConsumption = waterConsumption;
    }

    public Duration getDuration() {
        return duration;
    }

    public double getElectricityConsumption() {
        return electricityConsumption;
    }

    public double getWaterConsumption() {
        return waterConsumption;
    }

    public String toString() {
        return String.format("Dishwasher %s program", name);
    }
}
