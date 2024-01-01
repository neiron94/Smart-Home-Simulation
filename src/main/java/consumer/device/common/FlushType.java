package consumer.device.common;

import utils.Constants.Consumption.Water;

public enum FlushType {
    SMALL(Water.WC_SMALL),
    BIG(Water.WC_BIG);

    private final double waterConsumption;

    FlushType(double waterConsumption) {
        this.waterConsumption = waterConsumption;
    }

    public double getWaterConsumption() {
        return waterConsumption;
    }
}
