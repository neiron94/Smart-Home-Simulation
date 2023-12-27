package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.Manual;
import place.Room;
import utils.Percent;

public class Dryer extends Device implements ElectricityConsumer {

    private DryerProgram program;
    private boolean areClothesInside;
    private Percent filterStatus;
    private Time timeToReady;   // TODO - Time?

    public Dryer(int id, Room startRoom) {
        super(DeviceType.DRYER, id, startRoom);
        // TODO - set areClothesInside, program, timeToReady, filterStatus?
    }

    @Override
    public void routine() {
        super.routine();
        // TODO - doAction(): timeToReady--, if == 0 -> set STANDBY
    }

    @Override
    public void consumeElectricity() {
        // TODO - implement, depends on program
    }

    @Override
    public void fire() {
        // TODO - implement
    }

    public void dry(Time time, DryerProgram program) {
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
        filterStatus.setMax();
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

    public Percent getFilterStatus() {
        return filterStatus;
    }

    public void setFilterStatus(Percent filterStatus) {
        this.filterStatus = filterStatus;
    }

    public Time getTimeToReady() {
        return timeToReady;
    }

    public void setTimeToReady(Time timeToReady) {
        this.timeToReady = timeToReady;
    }
}
