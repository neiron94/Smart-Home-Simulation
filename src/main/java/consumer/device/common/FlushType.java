/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer.device.common;

import utils.Constants.Consumption.Water;

/**
 * Types of toilet flush.
 */
public enum FlushType {
    SMALL("Small", Water.WC_SMALL),
    BIG("Big", Water.WC_BIG);

    private final String name;
    private final double waterConsumption;

    FlushType(String name, double waterConsumption) {
        this.name = name;
        this.waterConsumption = waterConsumption;
    }

    public double getWaterConsumption() {
        return waterConsumption;
    }

    public String toString() {
        return String.format("%s flush", name);
    }
}
