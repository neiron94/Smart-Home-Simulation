package place.weather;

import place.Street;
import smarthome.Simulation;
import static utils.Constants.ConfigurationReaderConstants.*;
import java.time.Duration;
import java.util.Random;

/**
 * Windy weather.
 */
public class WindyWeather extends Weather {

    /**
     * Creates windy weather.
     */
    public WindyWeather() {
        super(Duration.ofMinutes(60));
    }

    @Override
    public void applyWeather() {
        int month = Simulation.getInstance().getCurrentTime().getMonthValue() - 1;
        int hour = Simulation.getInstance().getCurrentTime().getHour();

        Street.getInstance().setTemperature(stats[TEMPERATURE][month][hour] + new Random().nextInt(-7, -4));
        Street.getInstance().setHumidity(stats[HUMIDITY][month][hour] + new Random().nextInt(-4, -1));
        Street.getInstance().setBrightness(stats[BRIGHTNESS][month][hour] + new Random().nextInt(0, 1));
    }

    @Override
    public String toString() {
        return "Windy weather";
    }
}
