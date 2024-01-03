package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import place.Room;
import smarthome.Simulation;
import utils.Constants;
import utils.HelpFunctions;
import utils.exceptions.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class Dryer extends Device implements ElectricityConsumer {

    private DryerProgram program;
    private boolean areClothesInside;
    private double filterStatus;   // percent
    private LocalDateTime readyTime;

    public Dryer(int id, Room startRoom) {
        super(DeviceType.DRYER, id, startRoom);
        program = DryerProgram.COLD;
        areClothesInside = false;
        setFilterStatus(100);
        setReadyTime(program.getDuration());
    }

    //--------- Main public functions ----------//

    @Override
    public boolean routine() {
        if (!super.routine())   return false;

        if (status == DeviceStatus.ON) {
            setFilterStatus(filterStatus - Constants.FILTER_DEGRADATION);
            if (readyTime.isAfter(Simulation.getInstance().getCurrentTime()))
                status = DeviceStatus.STANDBY;
        }
        return true;
    }

    @Override
    public double consumeElectricity() {
        return program != null ? HelpFunctions.countElectricityConsumption(status, program.getElectricityConsumption()) : 0;
    }

    //---------- API for human -----------//

    public void turnOn() throws DeviceIsBrokenException, ResourceNotAvailableException {
        setStandby();
    }

    public void startDry(DryerProgram program) throws WrongDeviceStatusException, DirtyFilterException, EntryProblemException {
        if (program == null || status == DeviceStatus.ON) return;
        checkBeforeStart();

        setReadyTime(program.getDuration());
        this.program = program;
        status = DeviceStatus.ON;
    }

    public void putClothes() {
        areClothesInside = true;
    }

    public void takeClothes() {
        areClothesInside = false;
    }

    public void cleanFilter() {
        setFilterStatus(100);
    }

    //------------- Help functions -------------//

    private void checkBeforeStart() throws DirtyFilterException, EntryProblemException, WrongDeviceStatusException {
        checkDeviceStandby();
        if (filterStatus <= 0)
            throw new DirtyFilterException("Filter is too dirty.");
        if (!areClothesInside)
            throw new EntryProblemException("No clothes inside.");
    }

    //---------- Getters and Setters ----------//

    public DryerProgram getProgram() {
        return program;
    }

    public boolean isAreClothesInside() {
        return areClothesInside;
    }

    public double getFilterStatus() {
        return filterStatus;
    }

    private void setFilterStatus(double filterStatus) {
        this.filterStatus = HelpFunctions.adjustPercent(filterStatus);
    }

    public LocalDateTime getReadyTime() {
        return readyTime;
    }

    private void setReadyTime(Duration duration) {
        readyTime = Simulation.getInstance().getCurrentTime().plus(duration);
    }
}
