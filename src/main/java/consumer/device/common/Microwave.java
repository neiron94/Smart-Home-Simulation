package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import place.Room;
import smarthome.Simulation;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;
import utils.exceptions.DeviceIsBrokenException;
import utils.exceptions.EntryProblemException;
import utils.exceptions.ResourceNotAvailableException;
import utils.exceptions.WrongDeviceStatusException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Microwave extends Device implements ElectricityConsumer {
    private boolean isFoodInside;
    private int power;  // percent
    private LocalDateTime readyTime;

    public Microwave(int id, Room startRoom) {
        super(DeviceType.MICROWAVE, id, startRoom);
        isFoodInside = false;
        power = 0;
        readyTime = Simulation.getInstance().getCurrentTime();
    }

    //--------- Main public functions ----------//

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.MICROWAVE * power / 100);
    }

    @Override
    public boolean routine() {
        if (!super.routine()) return false;

        if (status == DeviceStatus.ON && Simulation.getInstance().getCurrentTime().isAfter(readyTime))
            stop();

        return true;
    }

    //---------- API for human -----------//

    public void turnOn() throws DeviceIsBrokenException, ResourceNotAvailableException {
        setStandby();
    }

    public void heatFood(Duration heatTime, int heatPower) throws WrongDeviceStatusException, EntryProblemException {
        if (heatTime == null) return;
        checkDeviceStandby();

        if (!isFoodInside)
            throw new EntryProblemException("No food inside.");

        setReadyTime(heatTime);
        setPower(heatPower);
        status = DeviceStatus.ON;
    }

    public void stop() {
        status = DeviceStatus.STANDBY;
    }

    public void putFood() {
        isFoodInside = true;
    }

    public void takeFood() {
        isFoodInside = false;
    }

    //---------- Getters and Setters ----------//

    public boolean isFoodInside() {
        return isFoodInside;
    }

    public int getPower() {
        return power;
    }

    private void setPower(int power) {
        this.power = HelpFunctions.adjustPercent(power);
    }

    public LocalDateTime getReadyTime() {
        return readyTime;
    }

    private void setReadyTime(Duration duration) {
        readyTime = Simulation.getInstance().getCurrentTime().plus(duration);
    }
}
