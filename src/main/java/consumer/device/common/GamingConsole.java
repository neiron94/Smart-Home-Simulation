/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.common.entertainment.Game;
import place.Room;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;
import utils.exceptions.DeviceIsOccupiedException;
import utils.exceptions.WrongDeviceStatusException;

/**
 * Gaming console. Person can play on it.
 */
public class GamingConsole extends Device implements ElectricityConsumer {

    private Game currentGame;

    public GamingConsole(int id, Room startRoom) {
        super(DeviceType.GAMING_CONSOLE, id, startRoom);
        currentGame = null;
    }

    //--------- Main public functions ----------//

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.GAMING_CONSOLE);
    }


    public GamingConsole copy() {
        return new GamingConsole(id, room);
    }

    //---------- API for human -----------//

    /**
     * Play specified game on console.
     * @param game game to play
     * @throws WrongDeviceStatusException if device is not in start status
     * @throws DeviceIsOccupiedException if device is occupied by someone else
     */
    public void play(Game game) throws WrongDeviceStatusException, DeviceIsOccupiedException {
        checkDeviceInStartStatus();
        checkDeviceNotOccupied();

        currentGame = game;
        status = DeviceStatus.ON;
        isOccupied = true;
    }

    /**
     * Stop playing console.
     */
    public void stop() {
        currentGame = null;
        restoreStatus();
    }

    //---------- Getters and Setters ----------//

    public Game getCurrentGame() {
        return currentGame;
    }
}
