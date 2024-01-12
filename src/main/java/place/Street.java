package place;

import place.weather.NormalWeather;
import place.weather.Weather;
import smarthome.Simulation;
import utils.ConfigurationReader;
import utils.HelpFunctions;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

/**
 * Singleton class representing street which can have different
 * temperature, humidity, brightness and weather which are dynamically changed.
 */
public class Street {
    private static Street INSTANCE;

    /**
     * Singleton getInstance method.
     * @return instance of Street
     */
    public static Street getInstance() {
        if (INSTANCE == null) INSTANCE = new Street();
        return INSTANCE;
    }

    private Weather weather;
    private LocalDateTime weatherChange;
    private double temperature;
    private double humidity;
    private double brightness;

    private Street() {
        weatherChange = Simulation.getInstance().getCurrentTime();
        weather = HelpFunctions.getRandomObject(Weather.weathers).orElse(new NormalWeather());
    }

    /**
     * Changes weather in a proper time, changes attributes of
     * the street every hour, is called every tick in {@link Simulation#simulate()}.
     */
    public void routine() {
        LocalDateTime currentTime = Simulation.getInstance().getCurrentTime();

        if (currentTime.equals(weatherChange)) weather.changeWeather();
        if (currentTime.getMinute() == 0) weather.applyWeather();
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

    public Weather getWeather() {
        return weather;
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

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public void setWeatherChange(LocalDateTime weatherChange) {
        this.weatherChange = weatherChange;
    }
}
