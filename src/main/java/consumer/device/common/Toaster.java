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
import utils.HelpFunctions;
import utils.exceptions.*;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Toaster for making toasts.
 */
public class Toaster extends Device implements ElectricityConsumer {
    private boolean isToastInside;
    private ToastType program;
    private LocalDateTime readyTime;

    public Toaster(int id, Room startRoom) {
        super(DeviceType.TOASTER, id, startRoom);
        isToastInside = false;
        program = ToastType.SANDWICH;
        setReadyTime(program.getCookTime());
    }

    //--------- Main public functions ----------//

    @Override
    public double consumeElectricity() {
        return program != null ? HelpFunctions.countElectricityConsumption(status, program.getPower()) : 0;
    }

    /**
     * Every tick check if should stop.
     * @return can be ignored
     */
    @Override
    public boolean routine() {
        if (!super.routine()) return false;

        if (status == DeviceStatus.ON && readyTime.isAfter(Simulation.getInstance().getCurrentTime()))
            restoreStatus();

        return true;
    }

    @Override
    public Toaster copy() {
        return new Toaster(id, room);
    }

    //---------- API for human -----------//

    /**
     * Start making toast.
     * @param program type of toast to make
     * @throws EntryProblemException if no toast inside toaster
     * @throws WrongDeviceStatusException if device is not in start status
     * @throws DeviceIsOccupiedException if device is occupied by someone else
     */
    public void makeToast(ToastType program) throws EntryProblemException, WrongDeviceStatusException, DeviceIsOccupiedException {
        checkDeviceInStartStatus();
        checkDeviceNotOccupied();
        if (!isToastInside)
            throw new EntryProblemException("No toast inside.");

        setReadyTime(program.getCookTime());
        this.program = program;
        status = DeviceStatus.ON;
        isOccupied = true;
    }

    /**
     * Put toast to toaster.
     */
    public void putToast() {
        isToastInside = true;
    }

    /**
     * Take toaster from toaster.
     */
    public void takeToast() {
        restoreStatus();
        isToastInside = false;
    }

    //---------- Getters and Setters ----------//

    public boolean isToastInside() {
        return isToastInside;
    }

    public ToastType getProgram() {
        return program;
    }

    public LocalDateTime getReadyTime() {
        return readyTime;
    }

    private void setReadyTime(Duration duration) {
        readyTime = Simulation.getInstance().getCurrentTime().plus(duration);
    }
}
