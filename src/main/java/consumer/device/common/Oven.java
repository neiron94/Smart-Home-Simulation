package consumer.device.common;

import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.Manual;
import place.Room;
import utils.HelpFunctions;

import java.time.LocalTime;

public abstract class Oven extends Device {
    protected static final double MAX_TEMPERATURE = 250;
    protected static final double MIN_TEMPERATURE = 0;

    protected boolean isFoodInside;
    protected double temperature;   // 0-250 °C
    protected LocalTime timeToReady;

    public Oven(DeviceType ovenType, int id, Room startRoom) {
        super(ovenType, id, startRoom);
    }

    @Override
    public boolean routine() {
        super.routine();
        // TODO - doAction(): timeToReady--, if == 0 -> set STANDBY
        return true;
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
        this.temperature = HelpFunctions.adjustToRange(temperature, MIN_TEMPERATURE, MAX_TEMPERATURE);
    }

    public LocalTime getTimeToReady() {
        return timeToReady;
    }

    public void setTimeToReady(LocalTime timeToReady) {
        this.timeToReady = timeToReady;
    }
}
