package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.Manual;
import creature.pet.PetType;
import place.Room;


public class Feeder extends Device implements ElectricityConsumer {

    private PetType type;
    private int foodFullness;   // TODO - comment what is what
    private int waterFullness;
    private int foodLevel;
    private int waterLevel;

    public Feeder(int id, Room startRoom, PetType type) {
        super(DeviceType.FEEDER, id, startRoom);
        this.type = type;
        // TODO - food/water fullness/level?
    }

    @Override
    public void consumeElectricity() {
        // TODO - implement
    }

    @Override
    public void fire() {
        // TODO - implement
    }

    @Override
    public void setStatus(DeviceStatus status) {
        if (status != DeviceStatus.STANDBY)
            this.status = status;
    }

    public void foodEmpty() {
        // TODO - implement, throw event
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

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public int getFoodFullness() {
        return foodFullness;
    }

    public void setFoodFullness(int foodFullness) {
        this.foodFullness = foodFullness;
    }

    public int getWaterFullness() {
        return waterFullness;
    }

    public void setWaterFullness(int waterFullness) {
        this.waterFullness = waterFullness;
    }

    public int getFoodLevel() {
        return foodLevel;
    }

    public void setFoodLevel(int foodLevel) {
        this.foodLevel = foodLevel;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }
}
