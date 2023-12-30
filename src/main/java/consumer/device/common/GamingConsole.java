package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.common.entertainment.Game;
import place.Room;
import utils.HelpFunctions;

public class GamingConsole extends Device implements ElectricityConsumer {

    private Game currentGame;

    public GamingConsole(int id, Room startRoom) {
        super(DeviceType.GAMING_CONSOLE, id, startRoom);
        currentGame = null; // TODO - null?
    }

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, 1);    // TODO - change 1 for Constant
    }

    public void play(Game game) {
        currentGame = game;
        status = DeviceStatus.ON;
        // TODO - maybe add something
    }

    public void stop() {
        // TODO - check this function
        currentGame = null;
        status = DeviceStatus.OFF;
    }

    // TODO - maybe delete some getters or setters

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }
}
