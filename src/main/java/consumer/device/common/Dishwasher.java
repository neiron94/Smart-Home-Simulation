package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.WaterConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.Manual;
import place.Room;
import utils.HelpFunctions;


public class Dishwasher extends Device implements WaterConsumer, ElectricityConsumer {

    private DishwasherProgram program;
    private int fullness;
    private int filterStatus;
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

    public void putDishes(int amount) {
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

    public int getFullness() {
        return fullness;
    }

    public void setFullness(int fullness) {
        this.fullness = fullness;
    }

    public int getFilterStatus() {
        return filterStatus;
    }

    public void setFilterStatus(int filterStatus) {
        this.filterStatus = filterStatus;
    }

    public Time getTimeToReady() {
        return timeToReady;
    }

    public void setTimeToReady(Time timeToReady) {
        this.timeToReady = timeToReady;
    }
}
