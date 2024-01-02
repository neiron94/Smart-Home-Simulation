package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceType;
import event.FillEvent;
import place.Room;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;
import utils.exceptions.DeviceIsBrokenException;
import utils.exceptions.ResourceNotAvailableException;
import utils.exceptions.WrongDeviceStatusException;


public class Feeder extends Device implements ElectricityConsumer {

    private static final int WATER_TANK_CAPACITY = 1500; // in milliliters
    private static final int FOOD_TANK_CAPACITY = 1800;  // in grams
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
    }

    //--------- Main public functions ----------//

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.FEEDER);
    }

    //---------- API for human/pet -----------//

    public void addFood() {
        setFoodFullness(FOOD_TANK_CAPACITY);
    }

    public void addWater() {
        setWaterFullness(WATER_TANK_CAPACITY);
    }

    public int drinkWater(int amount) throws WrongDeviceStatusException, DeviceIsBrokenException, ResourceNotAvailableException {
        int actualAmount = Math.min(waterLevel, amount);
        setWaterLevel(waterLevel - actualAmount);
        if (waterLevel == 0)
            fillWater();

        return actualAmount;
    }

    public int eatFood(int amount) throws WrongDeviceStatusException, DeviceIsBrokenException, ResourceNotAvailableException {
        int actualAmount = Math.min(foodLevel, amount);
        setFoodLevel(foodLevel - actualAmount);
        if (foodLevel == 0)
            fillFood();

        return actualAmount;
    }

    //------------- Help functions -------------//

    private void fillFood() throws WrongDeviceStatusException, DeviceIsBrokenException, ResourceNotAvailableException {
        checkDeviceOn();
        checkBeforeStatusSet();

        int fillAmount = Math.min(FOOD_DISH_CAPACITY, foodFullness);
        setFoodLevel(fillAmount);
        setFoodFullness(foodFullness - fillAmount);
        if (foodFullness <= 0) requestFill();
    }

    private void fillWater() throws WrongDeviceStatusException, DeviceIsBrokenException, ResourceNotAvailableException {
        checkDeviceOn();
        checkBeforeStatusSet();

        int fillAmount = Math.min(WATER_DISH_CAPACITY, waterFullness);
        setWaterLevel(fillAmount);
        setWaterFullness(waterFullness - fillAmount);
        if (waterFullness <= 0) requestFill();
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
