package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.Manual;

public class Fridge extends Device implements ElectricityConsumer {

    private int temperature;
    private Percent fullness;

    public Fridge(Room startRoom) {
        super(DeviceStatus.ON, null, startRoom);  // TODO - manual should be taken from somewhere
        // TODO - set fullness, temperature?
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
    public void setStatus(DeviceStatus status) {
        if (status != DeviceStatus.STANDBY)
            this.status = status;
    }

    public void orderFood() {
        // TODO - may last for some time?
        fullness.setMax();  // TODO - max?
    }

    public void takeFood(Percent amount) {
        // TODO - check amount?
        fullness.decrement(amount);
    }

    public void putFood(Percent amount) {
        // TODO - check amount?
        fullness.increment(amount);
    }

    // TODO - maybe delete some getters or setters

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public Percent getFullness() {
        return fullness;
    }

    public void setFullness(Percent fullness) {
        this.fullness = fullness;
    }
}
