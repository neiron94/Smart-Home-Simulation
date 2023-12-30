package place;


import utils.HelpFunctions;
import utils.Prototype;

import java.awt.Color;

public class RoomConfiguration implements Prototype {
    private String name;
    private float temperature;
    private int humidity;
    private int brightness;
    private Color color;

    public RoomConfiguration(String name, float temperature, int humidity, int brightness, Color color) {
        this.name = name;
        this.temperature = temperature;
        this.humidity = HelpFunctions.adjustPercent(humidity);
        this.brightness = HelpFunctions.adjustPercent(brightness);
        this.color = new Color(color.getRGB());
    }

    public String getName() {
        return name;
    }

    public float getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getBrightness() {
        return brightness;
    }

    public Color getColor() {
        return new Color(color.getRGB());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public void setHumidity(int humidity) {
        this.humidity = HelpFunctions.adjustPercent(humidity);
    }

    public void setBrightness(int brightness) {
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
