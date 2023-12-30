package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import place.Room;

public class CoffeeMachine extends Device implements ElectricityConsumer {

    private CoffeeType program;
    private int waterFullness;
    private int coffeeFullness;
    private int milkFullness;
    private final int WATER_CAPACITY = 0;   // TODO - move to Constants and give value
    private final int COFFEE_CAPACITY = 0;  // TODO - move to Constants and give value
    private final int MILK_CAPACITY = 0;    // TODO - move to Constants and give value

    public CoffeeMachine(int id, Room startRoom) {
        super(DeviceType.COFFEE_MACHINE, id, startRoom);
        // TODO - set program, waterFullness, coffeeFullness, milkFullness?
    }

    @Override
    public int consumeElectricity() {
        // TODO - implement, depends on program
        return 0;
    }

    @Override
    public void setStatus(DeviceStatus status) {
        if (status != DeviceStatus.STANDBY)
            this.status = status;
    }

    public void makeCoffee(CoffeeType program) {
        // TODO - check durability, water, milk, coffee
        this.program = program;
        waterFullness -= program.getWater();
        coffeeFullness -= program.getCoffee();
        milkFullness -= program.getMilk();
        // TODO - smth else?
    }

    public void fillWater(int amount) {
        waterFullness += amount;
        // TODO
    }

    public void fillCoffee(int amount) {
        coffeeFullness += amount;
        // TODO
    }

    public void fillMilk(int amount) {
        milkFullness += amount;
        // TODO
    }

    // TODO - maybe delete some getters or setters

    public CoffeeType getProgram() {
        return program;
    }

    public void setProgram(CoffeeType program) {
        this.program = program;
    }

    public int getWaterFullness() {
        return waterFullness;
    }

    public void setWaterFullness(int waterFullness) {
        this.waterFullness = waterFullness;
    }

    public int getCoffeeFullness() {
        return coffeeFullness;
    }

    public void setCoffeeFullness(int coffeeFullness) {
        this.coffeeFullness = coffeeFullness;
    }

    public int getMilkFullness() {
        return milkFullness;
    }

    public void setMilkFullness(int milkFullness) {
        this.milkFullness = milkFullness;
    }
}
