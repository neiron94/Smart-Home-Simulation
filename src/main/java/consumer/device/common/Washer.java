package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.WaterConsumer;
import consumer.device.Device;
import consumer.device.DeviceType;
import place.Room;
import utils.HelpFunctions;

import java.time.LocalTime;


public class Washer extends Device implements WaterConsumer, ElectricityConsumer {

    private LocalTime timeToReady;
    private WasherProgram program;
    private boolean areClothesInside;
    private int filterStatus;   // percent

    public Washer(int id, Room startRoom) {
        super(DeviceType.WASHER, id, startRoom);
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
        return program != null ? HelpFunctions.countElectricityConsumption(status, program.getPower()) : 0;
    }

    @Override
    public double consumeWater() {
        return program != null ? HelpFunctions.countWaterConsumption(status, program.getWaterConsumption()) : 0;
    }

    public void wash(WasherProgram program) {
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

    public LocalTime getTimeToReady() {
        return timeToReady;
    }

    public void setTimeToReady(LocalTime timeToReady) {
        this.timeToReady = timeToReady;
    }

    public WasherProgram getProgram() {
        return program;
    }

    public void setProgram(WasherProgram program) {
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
}
