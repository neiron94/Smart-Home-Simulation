package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import creature.pet.PetType;
import event.FillEvent;
import place.Room;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;


public class Feeder extends Device implements ElectricityConsumer {

    private PetType type;
    private int foodFullness; // percent  // TODO - comment what is what
    private int waterFullness;  // percent
    private int foodLevel;  // percent
    private int waterLevel; // percent

    public Feeder(int id, Room startRoom) {
        super(DeviceType.FEEDER, id, startRoom);
        // TODO - food/water fullness/level?
    }

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.FEEDER);
    }

    @Override
    public void setStatus(DeviceStatus status) {
        if (status != DeviceStatus.STANDBY)
            this.status = status;
    }

    public void requestFill() {
        new FillEvent(this, this.room).throwEvent();
    }

    public void addFood() {
        // TODO
    }

    public void addWater() {
        // TODO
    }

    public void fillFood() {
        // TODO
    }

    public void fillWater() {
        // TODO
    }

    public void checkFoodLevel() {
        // TODO
    }

    public void checkWaterLevel() {
        // TODO
    }

    // TODO - maybe delete some getters or setters

    public PetType getPetType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public int getFoodFullness() {
        return foodFullness;
    }

    public void setFoodFullness(int foodFullness) {
        this.foodFullness = HelpFunctions.adjustPercent(foodFullness);
    }

    public int getWaterFullness() {
        return waterFullness;
    }

    public void setWaterFullness(int waterFullness) {
        this.waterFullness = HelpFunctions.adjustPercent(waterFullness);
    }

    public int getFoodLevel() {
        return foodLevel;
    }

    public void setFoodLevel(int foodLevel) {
        this.foodLevel = HelpFunctions.adjustPercent(foodLevel);
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = HelpFunctions.adjustPercent(waterLevel);
    }
}
