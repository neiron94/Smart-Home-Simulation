package place;


import utils.ConfigurationReader;
import utils.HelpFunctions;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class ControlPanel {
    private final Room room;
    private RoomConfiguration currentConfiguration;

    private static final List<RoomConfiguration> configurations = new ArrayList<>();
    static {
        ConfigurationReader.readRoomConfigurationConfig();
    }

    public ControlPanel(Room room) {
        this.room = room;
        this.currentConfiguration = configurations.get(0).copy();
    }

    public Room getRoom() {
        return room;
    }

    public static List<RoomConfiguration> getConfigurations() {
        return configurations;
    }

    public static void addConfiguration(RoomConfiguration configuration) {
        configurations.add(configuration.copy());
    }

    public double getTemperature() {
        return currentConfiguration.getTemperature();
    }

    public void changeTemperature(double delta) {
        currentConfiguration.changeTemperature(delta);
    }

    public double getHumidity() {
        return currentConfiguration.getHumidity();
    }

    public void changeHumidity(double delta) {
        currentConfiguration.changeHumidity(delta);
    }

    public double getBrightness() {
        return currentConfiguration.getBrightness();
    }

    public void changeBrightness(double delta) {
        currentConfiguration.changeBrightness(delta);
    }

    public Color getColor() {
        return currentConfiguration.getColor();
    }

    public void setColor(Color color) {
        currentConfiguration.setColor(color);
    }

    public void saveConfiguration(String creatorName) {
        int id = 1;
        for (RoomConfiguration configuration : configurations)
            if (configuration.getName().startsWith(creatorName + "_"))
              id++;

        RoomConfiguration copy = currentConfiguration.copy();
        copy.setName(creatorName + "_" + id);
        configurations.add(copy);
    }

    public RoomConfiguration getRandomConfiguration() {
        return HelpFunctions.getRandomObject(configurations);
    }

    public void loadConfiguration(RoomConfiguration configuration) {
        currentConfiguration = configuration.copy();
    }

    public void loadConfiguration(String name) throws NoSuchElementException {
        for (RoomConfiguration configuration : configurations) {
            if (configuration.getName().equals(name)) {
                currentConfiguration = configuration.copy();
                return;
            }
        }
        throw new NoSuchElementException("No configuration with name " + name);
    }
}
