package consumer.device.common;

import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.Manual;
import place.Room;

import java.time.LocalTime;

public abstract class Oven extends Device {
    protected boolean isFoodInside;
    protected double temperature;
    protected LocalTime timeToReady;

    public Oven(DeviceType ovenType, int id, Room startRoom) {
        super(ovenType, id, startRoom);
    }

    @Override
    public void routine() {
        super.routine();
        // TODO - doAction(): timeToReady--, if == 0 -> set STANDBY
    }

    public void makeFood(LocalTime cookTime, int cookTemperature) {
        // TODO - check durability?, add smth?
        temperature = cookTemperature;
        timeToReady = cookTime;
    }

    public void putFood() {
        isFoodInside = true;
    }

    public void takeFood() {
        isFoodInside = false;
    }

    // TODO - maybe delete some getters or setters

    public boolean isFoodInside() {
        return isFoodInside;
    }

    public void setFoodInside(boolean foodInside) {
        isFoodInside = foodInside;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public LocalTime getTimeToReady() {
        return timeToReady;
    }

    public void setTimeToReady(LocalTime timeToReady) {
        this.timeToReady = timeToReady;
    }
}
