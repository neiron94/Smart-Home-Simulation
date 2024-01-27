/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceType;
import place.Room;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;
import utils.exceptions.DeviceIsBrokenException;
import utils.exceptions.ResourceNotAvailableException;
import utils.exceptions.WrongDeviceStatusException;

/**
 * Fridge for storing food. Can automatically order food.
 */
public class Fridge extends Device implements ElectricityConsumer {

    private static final double MIN_TEMPERATURE = 0;
    private static final double MAX_TEMPERATURE = 10;

    private double temperature; // 0-5 °C
    private int fullness;   // percent

    public Fridge(int id, Room startRoom) {
        super(DeviceType.FRIDGE, id, startRoom);
        fullness = 0;
        temperature = 0;
    }

    //--------- Main public functions ----------//

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.FRIDGE / 2 + Electricity.FRIDGE / 2 * (MAX_TEMPERATURE - temperature) / MAX_TEMPERATURE);
    }

    @Override
    public Fridge copy() {
        return new Fridge(id, room);
    }

    //---------- API for human -----------//

    /**
     * Take food from fridge. Fridge automatically orders food if fullness equals zero.
     * @param amount amount of taken food
     * @throws WrongDeviceStatusException if device is not in start status
     */
    public void takeFood(int amount) throws WrongDeviceStatusException {
        setFullness(fullness - amount);
        if (fullness <= 0) {
            try {
                orderFood();
            } catch (DeviceIsBrokenException | ResourceNotAvailableException ignored) {}
        }
    }

    /**
     * Put food to fridge.
     * @param amount amount of food to put
     */
    public void putFood(int amount) {
        setFullness(fullness + amount);
    }

    /**
     * Increase temperature of fridge.
     */
    public void increaseTemperature() {
        setTemperature(temperature + 1);
    }

    /**
     * Decrease temperature of fridge.
     */
    public void decreaseTemperature() {
        setTemperature(temperature - 1);
    }

    //------------- Help functions -------------//

    private void orderFood() throws DeviceIsBrokenException, ResourceNotAvailableException, WrongDeviceStatusException {
        checkBeforeTurnOn();
        checkDeviceInStartStatus();

        setFullness(100);
    }

    //---------- Getters and Setters ----------//

    public double getTemperature() {
        return temperature;
    }

    private void setTemperature(double temperature) {
        this.temperature = HelpFunctions.adjustToRange(temperature, MIN_TEMPERATURE, MAX_TEMPERATURE);
    }

    public int getFullness() {
        return fullness;
    }

    private void setFullness(int fullness) {
        this.fullness = HelpFunctions.adjustPercent(fullness);
    }
}
