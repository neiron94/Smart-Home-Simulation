package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceType;
import place.Room;
import utils.HelpFunctions;

import java.time.LocalTime;

public class Microwave extends Device implements ElectricityConsumer {
    private boolean isFoodInside;
    private int power;  // percent
    private LocalTime timeToReady;

    public Microwave(int id, Room startRoom) {
        super(DeviceType.MICROWAVE, id, startRoom);
        // TODO - set isFoodInside, power, timeToReady?
    }

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, 1.0 * power / 100);    // TODO - change 1.0 for Constant (max microwave kW)
    }

    @Override
    public void routine() {
        super.routine();
        // TODO - doAction(): timeToReady--, if == 0 -> set STANDBY
    }

    public void heatFood(LocalTime heatTime, int heatPower) {
        // TODO - check durability
        timeToReady = heatTime;
        power = HelpFunctions.adjustPercent(heatPower);
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
        this.power = HelpFunctions.adjustPercent(power);
    }

    public LocalTime getTimeToReady() {
        return timeToReady;
    }

    public void setTimeToReady(LocalTime timeToReady) {
        this.timeToReady = timeToReady;
    }
}
