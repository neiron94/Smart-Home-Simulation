package consumer.device.common;

import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import place.Room;
import smarthome.Simulation;
import utils.Constants;
import utils.HelpFunctions;
import utils.exceptions.DeviceIsOccupiedException;
import utils.exceptions.DirtyFilterException;
import utils.exceptions.EntryProblemException;
import utils.exceptions.WrongDeviceStatusException;

import java.time.Duration;
import java.time.LocalDateTime;

public abstract class CleaningDevice extends Device {
    protected double filterStatus;   // percent
    protected LocalDateTime readyTime;

    public CleaningDevice(DeviceType type, int id, Room startRoom) {
        super(type, id, startRoom);
        setFilterStatus(100);
        readyTime = Simulation.getInstance().getCurrentTime();
    }

    //--------- Main public functions ----------//

    @Override
    public boolean routine() {
        if (!super.routine())   return false;

        if (status == DeviceStatus.ON) {
            setFilterStatus(filterStatus - Constants.Degradation.FILTER_DEGRADATION);
            if (readyTime.isAfter(Simulation.getInstance().getCurrentTime()))
                restoreStatus();
        }
        return true;
    }

    //---------- API for human -----------//

    public void cleanFilter() {
        setFilterStatus(100);
    }

    //------------- Help functions -------------//

    protected void checkBeforeStart() throws DirtyFilterException, WrongDeviceStatusException, DeviceIsOccupiedException, EntryProblemException {
        checkDeviceInStartStatus();
        checkDeviceNotOccupied();
        if (filterStatus <= 0)
            throw new DirtyFilterException("Filter is too dirty.");
    }

    //---------- Getters and Setters ----------//

    public double getFilterStatus() {
        return filterStatus;
    }

    protected void setFilterStatus(double filterStatus) {
        this.filterStatus = HelpFunctions.adjustPercent(filterStatus);
    }

    public LocalDateTime getReadyTime() {
        return readyTime;
    }

    protected void setReadyTime(Duration duration) {
        readyTime = Simulation.getInstance().getCurrentTime().plus(duration);
    }
}
