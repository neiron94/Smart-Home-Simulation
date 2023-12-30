package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.WaterConsumer;
import consumer.device.Device;
import consumer.device.DeviceType;
import place.Room;


public class Washer extends Device implements WaterConsumer, ElectricityConsumer {

    private Time timeToReady;   // TODO - Time?
    private WasherProgram program;
    private boolean areClothesInside;
    private int filterStatus;

    public Washer(int id, Room startRoom) {
        super(DeviceType.WASHER, id, startRoom);
        // TODO - set areClothesInside, program, timeToReady, filterStatus?
    }

    @Override
    public void routine() {
        super.routine();
        // TODO - doAction(): timeToReady--, if == 0 -> set STANDBY
    }

    @Override
    public int consumeElectricity() {
        // TODO - implement, depends on program
        return 0;
    }

    @Override
    public void fire() {
        // TODO - implement
    }

    @Override
    public int consumeWater() {
        // TODO - implement, depends on program
        return 0;
    }

    @Override
    public void flood() {
        // TODO - implement
    }

    public void wash(Time time, WasherProgram program) {
        // TODO - check durability
        timeToReady = time;
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

    public Time getTimeToReady() {
        return timeToReady;
    }

    public void setTimeToReady(Time timeToReady) {
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
        this.filterStatus = filterStatus;
    }
}
