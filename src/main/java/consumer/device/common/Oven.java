package consumer.device.common;

import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import place.Room;
import smarthome.Simulation;
import utils.HelpFunctions;
import utils.exceptions.*;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Oven in which person can cook food.
 */
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

    /**
     * Every tick checks if should stop.
     * @return Can be ignored.
     */
    @Override
    public boolean routine() {
        if (!super.routine()) return false;

        if (status == DeviceStatus.ON && Simulation.getInstance().getCurrentTime().isAfter(readyTime))
            restoreStatus();

        return true;
    }

    //---------- API for human -----------//

    /**
     * Start cooking food.
     * @param cookTime Duration of cooking.
     * @param cookTemperature Temperature of cooking.
     * @throws EntryProblemException No food inside oven.
     * @throws DeviceIsOccupiedException Device is occupied by someone else.
     * @throws WrongDeviceStatusException Device is not in start status.
     */
    public void cookFood(Duration cookTime, int cookTemperature) throws EntryProblemException, DeviceIsOccupiedException, WrongDeviceStatusException {
        checkDeviceInStartStatus();
        checkDeviceNotOccupied();
        if (!isFoodInside)
            throw new EntryProblemException("No food inside.");

        setTemperature(cookTemperature);
        setReadyTime(cookTime);
        status = DeviceStatus.ON;
        isOccupied = true;
    }

    /**
     * Put food to oven.
     */
    public void putFood() {
        isFoodInside = true;
    }

    /**
     * Take food from oven.
     */
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
