package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.WaterConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import place.Room;
import utils.HelpFunctions;

import java.time.LocalTime;


public class Dishwasher extends Device implements WaterConsumer, ElectricityConsumer {

    private DishwasherProgram program;
    private int fullness;   // percent
    private int filterStatus;   // percent
    private LocalTime timeToReady;

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
    public double consumeElectricity() {
        return program != null ? HelpFunctions.countElectricityConsumption(status, program.getElectricityConsumption()) : 0;
    }

    @Override
    public double consumeWater() {
        return HelpFunctions.countWaterConsumption(status, program.getWaterConsumption());
    }

    public void wash(DishwasherProgram program) {
        // TODO - check durability
        timeToReady = program.getDuration();
        this.program = program;
        // TODO - smth else?
    }

    public void putDishes(int amount) {
        setFullness(fullness + amount);
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
        this.fullness = HelpFunctions.adjustPercent(fullness);
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
