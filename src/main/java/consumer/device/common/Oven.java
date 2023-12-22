package consumer.device.common;

import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.Manual;

public abstract class Oven extends Device {
    private boolean isFoodInside;
    private int temperature;
    private Time timeToReady;   // TODO - Time?

    public Oven(DeviceStatus startStatus, Manual manual, Room startRoom) {
        super(startStatus, manual, startRoom);
    }

    @Override
    public void routine() {
        super.routine();
        // TODO - doAction(): timeToReady--, if == 0 -> set STANDBY
    }

    public void makeFood(Time cookTime, int cookTemperature) {
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

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public Time getTimeToReady() {
        return timeToReady;
    }

    public void setTimeToReady(Time timeToReady) {
        this.timeToReady = timeToReady;
    }
}
