package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import place.Room;
import utils.HelpFunctions;


public class Fridge extends Device implements ElectricityConsumer {

    private double temperature;
    private int fullness;   // percent

    public Fridge(int id, Room startRoom) {
        super(DeviceType.FRIDGE, id, startRoom);
        // TODO - set fullness, temperature?
    }

    @Override
    public double consumeElectricity() {
        // TODO - should be reverse dependence
        return HelpFunctions.countElectricityConsumption(status, temperature / 2);      // TODO - change 2 for Constant (temperature for 1kW)
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

    public void takeFood(int amount) {
        // TODO - check amount?
        fullness = HelpFunctions.adjustPercent(fullness - amount);
    }

    public void putFood(int amount) {
        // TODO - check amount?
        fullness = HelpFunctions.adjustPercent(fullness + amount);
    }

    // TODO - maybe delete some getters or setters

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getFullness() {
        return fullness;
    }

    public void setFullness(int fullness) {
        this.fullness = HelpFunctions.adjustPercent(fullness);
    }
}
