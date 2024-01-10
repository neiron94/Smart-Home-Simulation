package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.WaterConsumer;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import place.Room;
import utils.HelpFunctions;
import utils.exceptions.*;

/**
 * Provides function for washing dishes.
 */
public class Dishwasher extends CleaningDevice implements WaterConsumer, ElectricityConsumer {

    private DishwasherProgram program;
    private int fullness;   // percent

    public Dishwasher(int id, Room startRoom) {
        super(DeviceType.DISHWASHER, id, startRoom);
        program = DishwasherProgram.LIGHT;
        fullness = 0;
    }

    //--------- Main public functions ----------//

    @Override
    public double consumeElectricity() {
        return program != null ? HelpFunctions.countElectricityConsumption(status, program.getElectricityConsumption()) : 0;
    }

    @Override
    public double consumeWater() {
        return HelpFunctions.countWaterConsumption(status, program.getWaterConsumption());
    }

    @Override
    public Dishwasher copy() {
        return new Dishwasher(id, room);
    }

    //---------- API for human -----------//

    /**
     * Start wash dishes.
     * @param program Program of washing. Determines required time and consumption.
     * @throws DirtyFilterException Filter of dishwasher is too dirty,
     * @throws EntryProblemException Too little dishes inside.
     * @throws WrongDeviceStatusException Device is not in start status.
     * @throws DeviceIsOccupiedException Someone else is using this device.
     */
    public void startWash(DishwasherProgram program) throws DirtyFilterException, EntryProblemException, WrongDeviceStatusException, DeviceIsOccupiedException {
        if (program == null) return;
        checkBeforeStart();

        setReadyTime(program.getDuration());
        this.program = program;
        status = DeviceStatus.ON;
        isOccupied = true;
    }

    /**
     * Add dishes to dishwasher.
     * @param amount amount of added dishes.
     */
    public void putDishes(int amount) {
        setFullness(fullness + amount);
    }

    /**
     * Take all dishes from dishwasher.
     */
    public void takeDishes() {
        restoreStatus();
        setFullness(0);
    }

    //------------- Help functions -------------//

    @Override
    protected void checkBeforeStart() throws DirtyFilterException, EntryProblemException, WrongDeviceStatusException, DeviceIsOccupiedException {
        super.checkBeforeStart();
        if (fullness < 80)
            throw new EntryProblemException("Too few dishes.");
    }

    //---------- Getters and Setters ----------//

    public DishwasherProgram getProgram() {
        return program;
    }

    public int getFullness() {
        return fullness;
    }

    private void setFullness(int fullness) {
        this.fullness = HelpFunctions.adjustPercent(fullness);
    }
}
