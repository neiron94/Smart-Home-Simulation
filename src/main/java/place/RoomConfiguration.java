package place;


import utils.HelpFunctions;
import utils.Prototype;

import java.awt.Color;

/**
 * Represents a snapshot of room condition which can be saved or loaded
 * by person using {@link ControlPanel}.
 */
public class RoomConfiguration implements Prototype {
    private static final double MIN_TEMPERATURE = 5;
    private static final double MAX_TEMPERATURE = 30;

    private String name;
    private double temperature;
    private double humidity;
    private double brightness;
    private Color color;

    /**
     * Creates new room configuration.
     * @param name name of configuration
     * @param temperature temperature of configuration
     * @param humidity humidity of configuration
     * @param brightness brightness of configuration
     * @param color color of configuration
     */
    public RoomConfiguration(String name, double temperature, double humidity, double brightness, Color color) {
        this.name = name;
        this.temperature = temperature;
        this.humidity = HelpFunctions.adjustPercent(humidity);
        this.brightness = HelpFunctions.adjustPercent(brightness);
        this.color = new Color(color.getRGB());
    }

    /**
     * Changes temperature of configuration.
     * @param delta change of temperature
     */
    public void changeTemperature(double delta) {
        this.temperature = HelpFunctions.adjustToRange(this.temperature + delta, MIN_TEMPERATURE, MAX_TEMPERATURE);
    }

    /**
     * Changes humidity of configuration.
     * @param delta change of humidity
     */
    public void changeHumidity(double delta) {
        this.humidity = HelpFunctions.adjustPercent(this.humidity + delta);
    }

    /**
     * Changes brightness of configuration.
     * @param delta change of brightness
     */
    public void changeBrightness(double delta) {
        this.brightness = HelpFunctions.adjustPercent(this.brightness + delta);
    }

    /**
     * Changes color of light of configuration.
     * @param color new color
     */
    public void setColor(Color color) {
        this.color = new Color(color.getRGB());
    }

    /**
     * Copies this configuration.
     * @return copy of this configuration
     */
    @Override
    public RoomConfiguration copy() {
        return new RoomConfiguration(name, temperature, humidity, brightness, new Color(color.getRGB()));
    }

    public String getName() {
        return name;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getBrightness() {
        return brightness;
    }

    public Color getColor() {
        return new Color(color.getRGB());
    }

    public void setName(String name) {
        this.name = name;
    }
}
