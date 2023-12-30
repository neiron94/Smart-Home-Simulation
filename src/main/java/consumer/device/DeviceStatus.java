package consumer.device;

public enum DeviceStatus {
    ON("ON", 1),
    OFF("OFF", 0),
    STANDBY("STANDBY", 0.01);

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
