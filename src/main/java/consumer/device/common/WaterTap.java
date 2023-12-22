package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.WaterConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.Manual;
import place.Room;
import utils.Percent;

public class WaterTap extends Device implements WaterConsumer, ElectricityConsumer {

    private int temperature;
    private Percent openness;

    public WaterTap(Room startRoom) {
        super(DeviceStatus.OFF, null, startRoom);     // TODO - manual should be taken from somewhere
        // TODO - set openness, temperature?
    }

    @Override
    public void consumeElectricity() {
        // TODO - implement, depends on temperature
    }

    @Override
    public void fire() {
        // TODO - implement
    }

    @Override
    public void consumeWater() {
        // TODO - implement, depends on openness
    }

    @Override
    public void flood() {
        // TODO - implement
    }

    public void open(int temperature, Percent openness) {
        // TODO - check durability
        this.temperature = temperature;
        this.openness = openness;
        this.status = DeviceStatus.ON;
        // TODO - smth else?
    }

    public void close() {
        this.status = DeviceStatus.OFF;
        openness.setMin();
        // TODO - smth else?
    }

    @Override
    public void setStatus(DeviceStatus status) {
        if (status != DeviceStatus.STANDBY)
            this.status = status;
    }

    // TODO - maybe delete some getters or setters

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public Percent getOpenness() {
        return openness;
    }

    public void setOpenness(Percent openness) {
        this.openness = openness;
    }
}
