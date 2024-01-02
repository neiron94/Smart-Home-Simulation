package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceType;
import place.Room;
import utils.HelpFunctions;

import java.time.LocalTime;


public class Dryer extends Device implements ElectricityConsumer {

    private DryerProgram program;
    private boolean areClothesInside;
    private int filterStatus;   // percent
    private LocalTime timeToReady;

    public Dryer(int id, Room startRoom) {
        super(DeviceType.DRYER, id, startRoom);
        // TODO - set areClothesInside, program, timeToReady, filterStatus?
    }

    @Override
    public boolean routine() {
        super.routine();
        // TODO - doAction(): timeToReady--, if == 0 -> set STANDBY
        return true;
    }

    @Override
    public double consumeElectricity() {
        return program != null ? HelpFunctions.countElectricityConsumption(status, program.getElectricityConsumption()) : 0;
    }

    public void dry(DryerProgram program) {
        // TODO - check durability
        timeToReady = program.getDuration();
        this.program = program;
        // TODO - smth else?
    }

    public void putClothes() {
        areClothesInside = true;
    }

    public void takeClothes() {
        areClothesInside = false;
    }

    public void cleanFilter() {
        filterStatus = 100;
    }

    // TODO - maybe delete some getters or setters

    public DryerProgram getProgram() {
        return program;
    }

    public void setProgram(DryerProgram program) {
        this.program = program;
    }

    public boolean isAreClothesInside() {
        return areClothesInside;
    }

    public void setAreClothesInside(boolean areClothesInside) {
        this.areClothesInside = areClothesInside;
    }

    public int getFilterStatus() {
        return filterStatus;
    }

    public void setFilterStatus(int filterStatus) {
        this.filterStatus = HelpFunctions.adjustPercent(filterStatus);
    }

    public LocalTime getTimeToReady() {
        return timeToReady;
    }

    public void setTimeToReady(LocalTime timeToReady) {
        this.timeToReady = timeToReady;
    }
}
