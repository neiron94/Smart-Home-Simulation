package consumer.device;

public enum DeviceStatus {
    ON("ON"),
    OFF("OFF"),
    STANDBY("STANDBY");

    private final String description;

    DeviceStatus(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
