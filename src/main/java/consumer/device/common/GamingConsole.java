package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.common.entertainment.Game;
import place.Room;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;
import utils.exceptions.DeviceIsBrokenException;
import utils.exceptions.ResourceNotAvailableException;
import utils.exceptions.WrongDeviceStatusException;

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

    //---------- API for human -----------//

    public void turnOn() throws DeviceIsBrokenException, ResourceNotAvailableException {
        setStandby();
    }

    public void play(Game game) throws WrongDeviceStatusException {
        checkDeviceStandby();

        currentGame = game;
        status = DeviceStatus.ON;
    }

    public void stop() {
        currentGame = null;
        setOff();
    }

    //---------- Getters and Setters ----------//

    public Game getCurrentGame() {
        return currentGame;
    }
}
