package consumer.device.common;

import utils.Constants.Consumption.Water;

public enum FlushType {
    SMALL("Small", Water.WC_SMALL),
    BIG("Big", Water.WC_BIG);

    private final String description;
    private final double waterConsumption;

    FlushType(String description, double waterConsumption) {
        this.description = description;
        this.waterConsumption = waterConsumption;
    }

    public String getDescription() {
        return description;
    }

    public double getWaterConsumption() {
        return waterConsumption;
    }
}
