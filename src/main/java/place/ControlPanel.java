package place;

import utils.Percent;
import java.awt.Color;
import java.util.List;

public class ControlPanel {
    private final Room room;
    private static List<RoomConfiguration> configurations;

    private float temperature;
    private Percent humidity;
    private Percent brightness;
    private Color color;

    public ControlPanel(Room room) { // TODO Maybe not to take room, but accept it
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public static List<RoomConfiguration> getConfigurations() {
        return configurations;
    }

    public static void addConfiguration(RoomConfiguration configuration) {
        configurations.add(configuration);
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public Percent getHumidity() {
        return humidity;
    }

    public void setHumidity(Percent humidity) {
        this.humidity = humidity;
    }

    public Percent getBrightness() {
        return brightness;
    }

    public void setBrightness(Percent brightness) {
        this.brightness = brightness;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void saveConfiguration(String name) { // TODO Memento pattern ???
        configurations.add(new RoomConfiguration(name, temperature, humidity, brightness, color));
    }

    public void loadConfiguration(RoomConfiguration configuration) { // TODO Memento pattern ???
        temperature = configuration.temperature();
        humidity = configuration.humidity();
        brightness = configuration.brightness();
        color = configuration.color();
    }
}
