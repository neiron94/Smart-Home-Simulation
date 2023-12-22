package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.Manual;
import creature.pet.PetType;
import place.Room;
import utils.Percent;

public class Feeder extends Device implements ElectricityConsumer {

    private PetType type;
    private Percent foodFullness;   // TODO - comment what is what
    private Percent waterFullness;
    private Percent foodLevel;
    private Percent waterLevel;

    public Feeder(Room startRoom) {
        super(DeviceStatus.ON, null, startRoom);  // TODO - manual should be taken from somewhere
        // TODO - set type, food/water fullness/level?
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

    public Percent getFoodFullness() {
        return foodFullness;
    }

    public void setFoodFullness(Percent foodFullness) {
        this.foodFullness = foodFullness;
    }

    public Percent getWaterFullness() {
        return waterFullness;
    }

    public void setWaterFullness(Percent waterFullness) {
        this.waterFullness = waterFullness;
    }

    public Percent getFoodLevel() {
        return foodLevel;
    }

    public void setFoodLevel(Percent foodLevel) {
        this.foodLevel = foodLevel;
    }

    public Percent getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(Percent waterLevel) {
        this.waterLevel = waterLevel;
    }
}
