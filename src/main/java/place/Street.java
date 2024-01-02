package place;

import smarthome.Simulation;
import utils.ConfigurationReader;
import utils.HelpFunctions;

import java.time.LocalDateTime;
import java.util.Random;

public class Street implements Location {
    private static final int OFFSET = 5;

    private static Street INSTANCE;
    public synchronized static Street getInstance() {
        if (INSTANCE == null) INSTANCE = new Street();
        return INSTANCE;
    }

    public static final double[][] temperatureStats = new double[12][24]; // TODO Put MONTHS=12 and HOURS=24 to constants
    public static final int[][] humidityStats = new int[12][24]; // TODO Put MONTHS=12 and HOURS=24 to constants
    public static final int[][] brightnessStats = new int[12][24]; // TODO Put MONTHS=12 and HOURS=24 to constants

    private double temperature;
    private int humidity;
    private int brightness;

    public Street() {
        ConfigurationReader.readWeatherConfig();
        // TODO Future feature - read city name from main configuration and load statistic weather data from internet
    }

    public double getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getBrightness() {
        return brightness;
    }

    @Override
    public void routine() {
        LocalDateTime currentTime = Simulation.getInstance().getCurrentTime();
        int month = currentTime.getMonthValue() - 1;
        int hour = currentTime.getHour();

        temperature = temperatureStats[month][hour]; // Get normal statistic temperature
        temperature = new Random().nextDouble(temperature - OFFSET, temperature + OFFSET); // Randomize temperature

        humidity = humidityStats[month][hour]; // Get normal statistic humidity
        humidity = HelpFunctions.adjustPercent(new Random().nextInt(humidity - OFFSET, humidity + OFFSET)); // Randomize humidity

        brightness = brightnessStats[month][hour]; // Get normal statistic brightness
        brightness = HelpFunctions.adjustPercent(new Random().nextInt(brightness - OFFSET, brightness + OFFSET)); // Randomize brightness
    }
}
