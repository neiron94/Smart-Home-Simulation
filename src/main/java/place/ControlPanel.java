package place;

import utils.HelpFunctions;
import java.awt.Color;
import java.util.*;

/**
 * Interface which allows {@link creature.person.Person} to save, load and change
 * {@link place.RoomConfiguration}. Control panel is attached to every {@link Room} automatically.
 */
public class ControlPanel {
    private static final List<RoomConfiguration> configurations = new ArrayList<>();

    private final Room room;
    public RoomConfiguration currentConfiguration;


    /**
     * Creates control panel with default room configuration.
     * Is called in {@link Room#Room(int, RoomType, Floor)} automatically.
     * @param room room in which this control panel is located
     */
    public ControlPanel(Room room) {
        this.room = room;
        this.currentConfiguration = configurations.get(0).copy();
    }

    /**
     * Saves current room configuration to the simulation of database.
     * @param creatorName name of creator, who saves configuration,
     *                    name of configuration will be generated in the form of "creatorName_id"
     *
     */
    public void saveConfiguration(String creatorName) {
        int id = 1;
        for (RoomConfiguration configuration : configurations)
            if (configuration.getName().startsWith(creatorName + "_"))
                id++;

        RoomConfiguration copy = currentConfiguration.copy();
        copy.setName(creatorName + "_" + id);
        configurations.add(copy);
    }

    /**
     * Gives random room configuration.
     * @return random room configuration
     */
    public Optional<RoomConfiguration> getRandomConfiguration() {
        return HelpFunctions.getRandomObject(configurations);
    }

    /**
     * Loads given configuration.
     * @param configuration the specified configuration
     */
    public void loadConfiguration(RoomConfiguration configuration) {
        currentConfiguration = configuration.copy();
    }

    /**
     * Loads configuration by the given name.
     * @param name name of configuration to load
     * @throws NoSuchElementException if no configuration with the specified name was found
     */
    public void loadConfiguration(String name) throws NoSuchElementException {
        for (RoomConfiguration configuration : configurations) {
            if (configuration.getName().equals(name)) {
                currentConfiguration = configuration.copy();
                return;
            }
        }
        throw new NoSuchElementException(String.format("No configuration with name '%s'", name));
    }

    /**
     * Adds given configuration to the simulation of database.
     * @param configuration configuration to add
     */
    public static void addConfiguration(RoomConfiguration configuration) {
        configurations.add(configuration.copy());
    }

    /**
     * Changes temperature of current configuration.
     * @param delta amount of temperature change
     */
    public void changeTemperature(double delta) {
        currentConfiguration.changeTemperature(delta);
    }

    /**
     * Changes humidity of current configuration.
     * @param delta amount of humidity change
     */
    public void changeHumidity(double delta) {
        currentConfiguration.changeHumidity(delta);
    }

    /**
     * Changes brightness of current configuration.
     * @param delta amount of brightness change
     */
    public void changeBrightness(double delta) {
        currentConfiguration.changeBrightness(delta);
    }

    /**
     * Changes color of light of current configuration.
     * @param color new color of configuration
     */
    public void setColor(Color color) {
        currentConfiguration.setColor(color);
    }

    public Room getRoom() {
        return room;
    }

    public static List<RoomConfiguration> getConfigurations() {
        return configurations;
    }

    public double getTemperature() {
        return currentConfiguration.getTemperature();
    }

    public double getHumidity() {
        return currentConfiguration.getHumidity();
    }

    public double getBrightness() {
        return currentConfiguration.getBrightness();
    }

    public Color getColor() {
        return currentConfiguration.getColor();
    }
}
