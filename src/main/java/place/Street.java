package place;

import smarthome.Simulation;
import utils.ConfigurationReader;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Street {
    private static final int TEMPERATURE = 0; // TODO Move to constants
    private static final int HUMIDITY = 1; // TODO Move to constants
    private final static int BRIGHTNESS = 2; // TODO Move to constants

    private static Street INSTANCE;
    public synchronized static Street getInstance() {
        if (INSTANCE == null) INSTANCE = new Street();
        return INSTANCE;
    }

    public static final double[][][] stats = new double[3][12][24]; // TODO Put MONTHS=12 and HOURS=24 to constants

    private Weather weather;
    private LocalDateTime weatherChange;
    private double temperature;
    private double humidity;
    private double brightness;

    public Street() {
        ConfigurationReader.readWeatherConfig();
        weatherChange = Simulation.getInstance().getCurrentTime();
        weather = Weather.NORMAL;
    }

    public Weather getWeather() {
        return weather;
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

    private void changeWeather(LocalDateTime time) {
        double choice = Math.random();
        double accumulator = 0.0;

        for (Map.Entry<Weather, Double> entry : weather.getProbability().entrySet()) {
            accumulator += entry.getValue();
            if (choice <= accumulator) {
                weather = entry.getKey();
                changeParameters(time);
                weatherChange = time.plus(weather.getDuration());
                break;
            }
        }
    }
    
    private void changeParameters(LocalDateTime time) {
        int month = time.getMonthValue() - 1;
        int hour = time.getHour();

        temperature = stats[TEMPERATURE][month][hour] + weather.getTemperatureEffect();
        humidity = stats[HUMIDITY][month][hour] + weather.getHumidityEffect();
        brightness = stats[BRIGHTNESS][month][hour] + weather.getBrightnessEffect();
    }

    public void routine() {
        LocalDateTime currentTime = Simulation.getInstance().getCurrentTime();

        if (currentTime.equals(weatherChange)) changeWeather(currentTime);
        if (currentTime.getMinute() == 0) changeParameters(currentTime);
    }
}
