package consumer.device.common;

import consumer.ConsumeVisitor;
import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import place.Room;
import utils.Constants;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;
import utils.exceptions.*;

/**
 * Machine for making different types of coffee. Requires certain amount of coffee, milk and water.
 */
public class CoffeeMachine extends Device implements ElectricityConsumer {
    private static final int WATER_CAPACITY = 1500;
    private static final int COFFEE_CAPACITY = 400;
    private static final int MILK_CAPACITY = 700;

    private int waterFullness;
    private int coffeeFullness;
    private int milkFullness;

    public CoffeeMachine(int id, Room startRoom) {
        super(DeviceType.COFFEE_MACHINE, id, startRoom);
        waterFullness = 0;
        coffeeFullness = 0;
        milkFullness = 0;
    }

    //--------- Main public functions ----------//

    /**
     * Counts amount of consumed electricity during this simulation tick.
     * @return Consumed electricity.
     */
    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.COFFEE_MACHINE);
    }

    @Override
    public CoffeeMachine copy() {
        return new CoffeeMachine(id, room);
    }

    //---------- API for human -----------//

    /**
     * Makes coffee.
     * @param program Type of coffee to make.
     * @throws EntryProblemException Not enough milk, water or coffee for this coffee type.
     * @throws WrongDeviceStatusException Device is not in start status.
     */
    public void makeCoffee(CoffeeType program) throws EntryProblemException, WrongDeviceStatusException {
        checkBeforeStart(program);

        waterFullness -= program.getWater();
        coffeeFullness -= program.getCoffee();
        milkFullness -= program.getMilk();
        decreaseDurability(Constants.Degradation.USE_DEGRADATION);
        status = DeviceStatus.ON;
        accept(new ConsumeVisitor());
        status = type.getStartStatus();
    }

    /**
     * Fill ingredients.
     */
    public void fillAll() {
        setCoffee(COFFEE_CAPACITY);
        setWater(WATER_CAPACITY);
        setMilk(MILK_CAPACITY);
    }

    //------------- Help functions -------------//

    private void checkBeforeStart(CoffeeType program) throws EntryProblemException, WrongDeviceStatusException {
        checkDeviceInStartStatus();
        checkIsEnough(program);
    }

    private void checkIsEnough(CoffeeType program) throws EntryProblemException {
        if (waterFullness < program.getWater() || milkFullness < program.getMilk() || coffeeFullness < program.getCoffee())
            throw new EntryProblemException("Not enough ingredients.");
    }

    //---------- Getters and Setters ----------//

    public int getWaterFullness() {
        return waterFullness;
    }

    private void setWater(int amount) {
        waterFullness = HelpFunctions.adjustToRange(waterFullness + amount, 0, WATER_CAPACITY);
    }

    public int getCoffeeFullness() {
        return coffeeFullness;
    }

    private void setCoffee(int amount) {
        coffeeFullness = HelpFunctions.adjustToRange(coffeeFullness + amount, 0, COFFEE_CAPACITY);
    }

    public int getMilkFullness() {
        return milkFullness;
    }

    private void setMilk(int amount) {
        milkFullness = HelpFunctions.adjustToRange(milkFullness + amount, 0, MILK_CAPACITY);
    }
}
