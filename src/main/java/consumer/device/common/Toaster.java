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

    @Override
    public boolean routine() {
        if (!super.routine()) return false;

        if (status == DeviceStatus.ON && readyTime.isAfter(Simulation.getInstance().getCurrentTime()))
            restoreStatus();

        return true;
    }

    //---------- API for human -----------//

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

    public void putToast() {
        isToastInside = true;
    }

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
