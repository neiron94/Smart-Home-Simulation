package place.weather;

import place.Street;
import smarthome.Simulation;
import static utils.Constants.ConfigurationReaderConstants.*;
import java.time.Duration;
import java.util.Random;

/**
 * Rainy weather.
 */
public class RainyWeather extends Weather {

    protected RainyWeather() {
        super(Duration.ofMinutes(90));
    }

    @Override
    public void applyWeather() {
        int month = Simulation.getInstance().getCurrentTime().getMonthValue() - 1;
        int hour = Simulation.getInstance().getCurrentTime().getHour();

        Street.getInstance().setTemperature(stats[TEMPERATURE][month][hour] + new Random().nextInt(-5, -2));
        Street.getInstance().setHumidity(stats[HUMIDITY][month][hour] + new Random().nextInt(7, 13));
        Street.getInstance().setBrightness(stats[BRIGHTNESS][month][hour] + new Random().nextInt(-17, -12));
    }

    @Override
    public String toString() {
        return "Rainy weather";
    }
}
