package place;


import utils.HelpFunctions;
import utils.Prototype;

import java.awt.Color;

public class RoomConfiguration implements Prototype {
    private String name;
    private double temperature;
    private double humidity;
    private double brightness;
    private Color color;

    public RoomConfiguration(String name, double temperature, double humidity, double brightness, Color color) {
        this.name = name;
        this.temperature = temperature;
        this.humidity = HelpFunctions.adjustPercent(humidity);
        this.brightness = HelpFunctions.adjustPercent(brightness);
        this.color = new Color(color.getRGB());
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

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setHumidity(double humidity) {
        this.humidity = HelpFunctions.adjustPercent(humidity);
    }

    public void setBrightness(double brightness) {
        this.brightness = HelpFunctions.adjustPercent(brightness);
    }

    public void setColor(Color color) {
        this.color = new Color(color.getRGB());
    }

    @Override
    public RoomConfiguration copy() {
        return new RoomConfiguration(name, temperature, humidity, brightness, color);
    }
}
