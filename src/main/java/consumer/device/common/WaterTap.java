/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.WaterConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import place.Room;
import utils.Constants.Consumption.Water;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;
import utils.exceptions.DeviceIsOccupiedException;
import utils.exceptions.WrongDeviceStatusException;

/**
 * Electric water tap with automatic heat which can be also interpreted as a shower (if it is in SHOWER room).
 */
public class WaterTap extends Device implements WaterConsumer, ElectricityConsumer {
    private static final double MAX_TEMPERATURE = 60;
    private static final double MIN_TEMPERATURE = 10;

    private double temperature; // 10-60 °C
    private int openness;   // percent

    public WaterTap(int id, Room startRoom) {
        super(DeviceType.WATER_TAP, id, startRoom);
        setOpenness(0);
        setTemperature(0);
    }

    //--------- Main public functions ----------//

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.WATER_TAP * temperature / MAX_TEMPERATURE);
    }

    @Override
    public double consumeWater() {
        return HelpFunctions.countWaterConsumption(status, Water.WATER_TAP * openness / 100);
    }

    @Override
    public WaterTap copy() {
        return new WaterTap(id, room);
    }

    //---------- API for human -----------//

    /**
     * Open water tap with given temperature and openness.
     * @param temperature temperature of water
     * @param openness openness of water tap
     * @throws DeviceIsOccupiedException if device is occupied by someone else
     * @throws WrongDeviceStatusException if device is not in start status
     */
    public void open(double temperature, int openness) throws DeviceIsOccupiedException, WrongDeviceStatusException {
        checkDeviceInStartStatus();
        checkDeviceNotOccupied();

        setTemperature(temperature);
        setOpenness(openness);
        this.status = DeviceStatus.ON;
        isOccupied = true;
    }

    /**
     * Close water tap.
     */
    public void close() {
        openness = 0;
        restoreStatus();
    }

    //---------- Getters and Setters ----------//

    public double getTemperature() {
        return temperature;
    }

    private void setTemperature(double temperature) {
        this.temperature = HelpFunctions.adjustToRange(temperature, MIN_TEMPERATURE, MAX_TEMPERATURE);
    }

    public int getOpenness() {
        return openness;
    }

    private void setOpenness(int openness) {
        this.openness = HelpFunctions.adjustPercent(openness);
    }
}
