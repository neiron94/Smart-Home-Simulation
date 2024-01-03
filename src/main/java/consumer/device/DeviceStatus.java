package consumer.device;

import utils.Constants.Consumption;

public enum DeviceStatus {
    ON("ON", Consumption.ON_MULTIPLIER),
    OFF("OFF", Consumption.OFF_MULTIPLIER),
    STANDBY("STANDBY", Consumption.STANDBY_MULTIPLIER);

    private final String description;
    private final double consumeMultiplier;

    DeviceStatus(String description, double consumeMultiplier) {
        this.description = description;
        this.consumeMultiplier = consumeMultiplier;
    }

    public double getMultiplier() {
        return consumeMultiplier;
    }

    @Override
    public String toString() {
        return description;
    }
}
