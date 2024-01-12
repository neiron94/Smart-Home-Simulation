package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceType;
import event.FillEvent;
import place.Room;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;
import utils.exceptions.WrongDeviceStatusException;

/**
 * Feeder for animals. Automatically adds water and food, notifies people
 * that it should be filled.
 */
public class Feeder extends Device implements ElectricityConsumer {

    private static final int WATER_TANK_CAPACITY = 1500; // in milliliters
    private static final int FOOD_TANK_CAPACITY = 1000;  // in grams
    private static final int WATER_DISH_CAPACITY = 300; // in milliliters
    private static final int FOOD_DISH_CAPACITY = 200;  // in grams

    private int foodFullness; // in grams, inner fullness
    private int waterFullness;  // in milliliters, inner fullness
    private int foodLevel;  // in grams, dish fullness
    private int waterLevel; // in milliliters, dish fullness

    public Feeder(int id, Room startRoom) {
        super(DeviceType.FEEDER, id, startRoom);
        foodFullness = 0;
        waterFullness = 0;
        foodLevel = 0;
        waterLevel = 0;
        requestFill();
    }

    //--------- Main public functions ----------//

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.FEEDER);
    }

    @Override
    public Feeder copy() {
        return new Feeder(id, room);
    }

    //---------- API for human/pet -----------//

    /**
     * Add food to tank. Should be used by person solving FillEvent.
     */
    public void addFood() {
        setFoodFullness(FOOD_TANK_CAPACITY);
    }

    /**
     * Add water to tank. Should be used by person solving FillEvent.
     */
    public void addWater() {
        setWaterFullness(WATER_TANK_CAPACITY);
    }

    /**
     * Drink water from a pan. Should be used by animals.
     * @param amount Amount of water to drink.
     */
    public void drinkWater(int amount) {
        setWaterLevel(waterLevel - amount);
        try {
            if (waterLevel == 0) fillWater();
        } catch (WrongDeviceStatusException ignored) {}
    }

    /**
     * Eat food from a pan. Should be used by animals.
     * @param amount Amount of food to eat.
     */
    public void eatFood(int amount) {
        setFoodLevel(foodLevel - amount);
        try {
            if (foodLevel == 0) fillFood();
        } catch (WrongDeviceStatusException ignored) {}
    }

    //------------- Help functions -------------//

    private void fillFood() throws WrongDeviceStatusException {
        checkDeviceInStartStatus();

        int fillAmount = Math.min(FOOD_DISH_CAPACITY, foodFullness);
        setFoodLevel(fillAmount);
        setFoodFullness(foodFullness - fillAmount);
        if (foodFullness == 0) requestFill();
    }

    private void fillWater() throws WrongDeviceStatusException {
        checkDeviceInStartStatus();

        int fillAmount = Math.min(WATER_DISH_CAPACITY, waterFullness);
        setWaterLevel(fillAmount);
        setWaterFullness(waterFullness - fillAmount);
        if (waterFullness == 0) requestFill();
    }

    private void requestFill() {
        new FillEvent(this, this.room).throwEvent();
    }

    //---------- Getters and Setters ----------//

    public int getFoodFullness() {
        return foodFullness;
    }

    private void setFoodFullness(int foodFullness) {
        this.foodFullness = HelpFunctions.adjustToRange(foodFullness, 0, FOOD_TANK_CAPACITY);
    }

    public int getWaterFullness() {
        return waterFullness;
    }

    private void setWaterFullness(int waterFullness) {
        this.waterFullness = HelpFunctions.adjustToRange(waterFullness, 0, WATER_TANK_CAPACITY);
    }

    public int getFoodLevel() {
        return foodLevel;
    }

    private void setFoodLevel(int foodLevel) {
        this.foodLevel = HelpFunctions.adjustToRange(foodLevel, 0, FOOD_DISH_CAPACITY);
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    private void setWaterLevel(int waterLevel) {
        this.waterLevel = HelpFunctions.adjustToRange(waterLevel, 0, WATER_DISH_CAPACITY);
    }
}
