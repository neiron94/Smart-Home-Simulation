package consumer.device.common;

import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import place.Room;
import smarthome.Simulation;
import utils.HelpFunctions;
import utils.exceptions.DeviceIsBrokenException;
import utils.exceptions.EntryProblemException;
import utils.exceptions.ResourceNotAvailableException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public abstract class Oven extends Device {
    protected static final double MAX_TEMPERATURE = 250;
    protected static final double MIN_TEMPERATURE = 0;

    protected boolean isFoodInside;
    protected double temperature;   // 0-250 °C
    protected LocalDateTime readyTime;

    public Oven(DeviceType ovenType, int id, Room startRoom) {
        super(ovenType, id, startRoom);
        isFoodInside = false;
        setTemperature(MIN_TEMPERATURE);
        readyTime = Simulation.getInstance().getCurrentTime();
    }

    //--------- Main public functions ----------//

    @Override
    public boolean routine() {
        if (!super.routine()) return false;

        if (status == DeviceStatus.ON && Simulation.getInstance().getCurrentTime().isAfter(readyTime))
            stop();

        return true;
    }

    //---------- API for human -----------//

    public void makeFood(Duration cookTime, int cookTemperature) throws DeviceIsBrokenException, ResourceNotAvailableException, EntryProblemException {
        checkBeforeStatusSet();
        if (!isFoodInside)
            throw new EntryProblemException("No food inside.");

        setTemperature(cookTemperature);
        setReadyTime(cookTime);
        status = DeviceStatus.ON;
    }

    public void stop() {
        setOff();
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

    public double getTemperature() {
        return temperature;
    }

    private void setTemperature(double temperature) {
        this.temperature = HelpFunctions.adjustToRange(temperature, MIN_TEMPERATURE, MAX_TEMPERATURE);
    }

    public LocalDateTime getReadyTime() {
        return readyTime;
    }

    private void setReadyTime(Duration duration) {
        readyTime = Simulation.getInstance().getCurrentTime().plus(duration);
    }
}
