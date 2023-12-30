package consumer.device.common;

public enum FlushType {
    SMALL(0),   // TODO - set values
    BIG(0);

    private final double waterConsumption;

    FlushType(double waterConsumption) {
        this.waterConsumption = waterConsumption;
    }

    public double getWaterConsumption() {
        return waterConsumption;
    }
}
