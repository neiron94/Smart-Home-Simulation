package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import place.Room;

public class Microwave extends Device implements ElectricityConsumer {
    private boolean isFoodInside;
    private int power;
    private Time timeToReady;   // TODO -Time?

    public Microwave(int id, Room startRoom) {
        super(DeviceType.MICROWAVE, id, startRoom);
        // TODO - set isFoodInside, power, timeToReady?
    }

    @Override
    public void consumeElectricity() {
        // TODO - implement, depends on power
    }

    @Override
    public void fire() {
        // TODO - implement
    }

    @Override
    public void routine() {
        super.routine();
        // TODO - doAction(): timeToReady--, if == 0 -> set STANDBY
    }

    public void heatFood(Time heatTime, int heatPower) {
        // TODO - check durability
        timeToReady = heatTime;
        power = heatPower;
        // TODO - smth else?
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

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Time getTimeToReady() {
        return timeToReady;
    }

    public void setTimeToReady(Time timeToReady) {
        this.timeToReady = timeToReady;
    }
}
