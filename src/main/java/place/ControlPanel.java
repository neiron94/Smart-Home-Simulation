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

    public float getTemperature() {
        return currentConfiguration.getTemperature();
    }

    public void setTemperature(float temperature) {
        currentConfiguration.setTemperature(temperature);
    }

    public void increaseTemperature(float value) {
        currentConfiguration.setTemperature(currentConfiguration.getTemperature() + value);
    }

    public void decreaseTemperature(float value) {
        currentConfiguration.setTemperature(currentConfiguration.getTemperature() - value);
    }

    public float getHumidity() {
        return currentConfiguration.getHumidity();
    }

    public void setHumidity(int humidity) {
        currentConfiguration.setHumidity(humidity);
    }

    public void increaseHumidity(int value) {
        currentConfiguration.setHumidity(currentConfiguration.getHumidity() + value);
    }

    public void decreaseHumidity(int value) {
        currentConfiguration.setHumidity(currentConfiguration.getHumidity() - value);
    }

    public float getBrightness() {
        return currentConfiguration.getBrightness();
    }

    public void setBrightness(int brightness) {
        currentConfiguration.setBrightness(brightness);
    }

    public void increaseBrightness(int value) {
        currentConfiguration.setBrightness(currentConfiguration.getBrightness() + value);
    }

    public void decreaseBrightness(int value) {
        currentConfiguration.setBrightness(currentConfiguration.getBrightness() - value);
    }

    public Color getColor() {
        return currentConfiguration.getColor();
    }

    public void setColor(Color color) {
        currentConfiguration.setColor(color);
    }

    public void saveConfiguration(String name) {
        RoomConfiguration copy = currentConfiguration.copy();
        copy.setName(name);
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
