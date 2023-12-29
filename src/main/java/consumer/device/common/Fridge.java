package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.Manual;
import place.Room;
import utils.HelpFunctions;
import utils.Percent;

public class Fridge extends Device implements ElectricityConsumer {

    private int temperature;
    private Percent fullness;

    public Fridge(int id, Room startRoom) {
        super(DeviceType.FRIDGE, id, startRoom);
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
        fullness = 100;  // TODO - 100?
    }

    public void takeFood(Percent amount) {
        // TODO - check amount?
        fullness = HelpFunctions.adjustPercent(fullness - amount);
    }

    public void putFood(Percent amount) {
        // TODO - check amount?
        fullness = HelpFunctions.adjustPercent(fullness + amount);
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
