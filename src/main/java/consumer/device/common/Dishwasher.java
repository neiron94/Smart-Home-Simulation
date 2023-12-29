package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.WaterConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.Manual;
import place.Room;
import utils.HelpFunctions;
import utils.Percent;

public class Dishwasher extends Device implements WaterConsumer, ElectricityConsumer {

    private DishwasherProgram program;
    private Percent fullness;
    private Percent filterStatus;
    private Time timeToReady;   // TODO - Time?

    public Dishwasher(int id, Room startRoom) {
        super(DeviceType.DISHWASHER, id, startRoom);
        // TODO - set fullness, program, timeToReady, filterStatus?
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

    @Override
    public void consumeWater() {
        // TODO - implement, depends on program
    }

    @Override
    public void flood() {
        // TODO - implement
    }

    public void wash(DishwasherProgram program) {
        // TODO - check durability
        // TODO - timeToReady = program.getTime();
        this.program = program;
        // TODO - smth else?
    }

    public void putDishes(Percent amount) {
        fullness = HelpFunctions.adjustPercent(fullness + amount);
    }

    public void takeDishes() {
        fullness = 0;
    }

    public void cleanFilter() {
        filterStatus = 100;
    }

    // TODO - maybe delete some getters or setters

    public DishwasherProgram getProgram() {
        return program;
    }

    public void setProgram(DishwasherProgram program) {
        this.program = program;
    }

    public Percent getFullness() {
        return fullness;
    }

    public void setFullness(Percent fullness) {
        this.fullness = fullness;
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
