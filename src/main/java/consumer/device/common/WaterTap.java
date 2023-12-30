package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.WaterConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import place.Room;


public class WaterTap extends Device implements WaterConsumer, ElectricityConsumer {

    private int temperature;
    private int openness;

    public WaterTap(int id, Room startRoom) {
        super(DeviceType.WATER_TAP, id, startRoom);
        // TODO - set openness, temperature?
    }

    @Override
    public int consumeElectricity() {
        // TODO - implement, depends on temperature
        return 0;
    }

    @Override
    public void fire() {
        // TODO - implement
    }

    @Override
    public int consumeWater() {
        // TODO - implement, depends on openness
        return 0;
    }

    @Override
    public void flood() {
        // TODO - implement
    }

    public void open(int temperature, int openness) {
        // TODO - check durability
        this.temperature = temperature;
        this.openness = openness;
        this.status = DeviceStatus.ON;
        // TODO - smth else?
    }

    public void close() {
        this.status = DeviceStatus.OFF;
        openness = 0;
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

    public int getOpenness() {
        return openness;
    }

    public void setOpenness(int openness) {
        this.openness = openness;
    }
}
