package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import place.Room;

import java.util.List;

public class GamingConsole extends Device implements ElectricityConsumer {

    public static List<Game> games; // simulation of database of games TODO - should be preloaded, maybe in a static block

    private Game currentGame;

    public GamingConsole(int id, Room startRoom) {
        super(DeviceType.GAMING_CONSOLE, id, startRoom);
        currentGame = null; // TODO - null?
    }

    @Override
    public void consumeElectricity() {
        // TODO - implement
    }

    @Override
    public void fire() {
        // TODO - implement
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

    public static List<Game> getGames() {
        return games;
    }

    public static void setGames(List<Game> games) {
        GamingConsole.games = games;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }
}
