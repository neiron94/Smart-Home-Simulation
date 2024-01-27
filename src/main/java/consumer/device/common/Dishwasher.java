/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

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
     * @param program program of washing, determines required time and consumption
     * @throws DirtyFilterException if filter of dishwasher is too dirty
     * @throws EntryProblemException if too little dishes inside
     * @throws WrongDeviceStatusException if device is not in start status
     * @throws DeviceIsOccupiedException if someone else is using this device
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
     * @param amount amount of added dishes
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
