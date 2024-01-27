/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer.device.common;

import utils.Constants.WorkDuration;
import utils.Constants.Consumption.Electricity;
import java.time.Duration;

/**
 * Dryer programs. Stores information about programs' duration and consumption.
 */
public enum DryerProgram {
    COLD("Cold", WorkDuration.DRYER_COLD, Electricity.DRYER_COLD),
    NORMAL("Normal", WorkDuration.DRYER_NORMAL, Electricity.DRYER_NORMAL),
    HOT("Hot", WorkDuration.DRYER_HOT, Electricity.DRYER_HOT);

    private final String name;
    private final Duration duration;
    private final double electricityConsumption;

    DryerProgram(String name, Duration duration, double electricityConsumption) {
        this.name = name;
        this.duration = duration;
        this.electricityConsumption = electricityConsumption;
    }

    public Duration getDuration() {
        return duration;
    }

    public double getElectricityConsumption() {
        return electricityConsumption;
    }

    public String toString() {
        return String.format("Dryer %s program", name);
    }
}
