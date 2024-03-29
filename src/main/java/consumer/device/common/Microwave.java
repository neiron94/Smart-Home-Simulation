/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import place.Room;
import smarthome.Simulation;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;
import utils.exceptions.*;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Microwave for heating food.
 */
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

    /**
     * Every tick checks if should stop.
     * @return can be ignored
     */
    @Override
    public boolean routine() {
        if (!super.routine()) return false;

        if (status == DeviceStatus.ON && Simulation.getInstance().getCurrentTime().isAfter(readyTime))
            restoreStatus();

        return true;
    }

    @Override
    public Microwave copy() {
        return new Microwave(id, room);
    }

    //---------- API for human -----------//

    /**
     * Heat food.
     * @param heatTime duration of heating
     * @param heatPower power of heating
     * @throws WrongDeviceStatusException if device is not in start status
     * @throws EntryProblemException if no food inside microwave
     * @throws DeviceIsOccupiedException if device is occupied by someone else
     */
    public void heatFood(Duration heatTime, int heatPower) throws WrongDeviceStatusException, EntryProblemException, DeviceIsOccupiedException {
        if (heatTime == null) return;
        checkDeviceInStartStatus();
        checkDeviceNotOccupied();
        if (!isFoodInside)
            throw new EntryProblemException("No food inside.");

        setReadyTime(heatTime);
        setPower(heatPower);
        status = DeviceStatus.ON;
        isOccupied = true;
    }

    /**
     * Put food to microwave.
     */
    public void putFood() {
        isFoodInside = true;
    }

    /**
     * Take food from microwave.
     */
    public void takeFood() {
        restoreStatus();
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
