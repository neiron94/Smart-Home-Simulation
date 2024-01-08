package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.WaterConsumer;
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


public class Dishwasher extends Device implements WaterConsumer, ElectricityConsumer {

    private DishwasherProgram program;
    private int fullness;   // percent
    private double filterStatus;   // percent
    private LocalDateTime readyTime;

    public Dishwasher(int id, Room startRoom) {
        super(DeviceType.DISHWASHER, id, startRoom);
        program = DishwasherProgram.LIGHT;
        fullness = 0;
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
                restoreStatus();
        }
        return true;
    }

    @Override
    public double consumeElectricity() {
        return program != null ? HelpFunctions.countElectricityConsumption(status, program.getElectricityConsumption()) : 0;
    }

    @Override
    public double consumeWater() {
        return HelpFunctions.countWaterConsumption(status, program.getWaterConsumption());
    }

    //---------- API for human -----------//

    public void startWash(DishwasherProgram program) throws DirtyFilterException, EntryProblemException, WrongDeviceStatusException, DeviceIsOccupiedException {
        if (program == null) return;
        checkBeforeStart();

        setReadyTime(program.getDuration());
        this.program = program;
        status = DeviceStatus.ON;
        isOccupied = true;
    }

    public void putDishes(int amount) {
        setFullness(fullness + amount);
    }

    public void takeDishes() {
        restoreStatus();
        setFullness(0);
    }

    public void cleanFilter() {
        setFilterStatus(100);
    }

    //------------- Help functions -------------//

    private void checkBeforeStart() throws DirtyFilterException, EntryProblemException, WrongDeviceStatusException, DeviceIsOccupiedException {
        checkDeviceInStartStatus();
        checkDeviceNotOccupied();
        if (filterStatus <= 0)
            throw new DirtyFilterException("Filter is too dirty.");
        if (fullness < 80)
            throw new EntryProblemException("Too few dishes.");
    }

    //---------- Getters and Setters ----------//

    public DishwasherProgram getProgram() {
        return program;
    }

    public int getFullness() {
        return fullness;
    }

    private void setFullness(int fullness) {
        this.fullness = HelpFunctions.adjustPercent(fullness);
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
