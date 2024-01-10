package place.weather;

import place.Street;
import smarthome.Simulation;
import static utils.Constants.ConfigurationReaderConstants.*;
import java.time.Duration;
import java.util.Random;

public class SunnyWeather extends Weather {
    private final Duration duration = Duration.ofMinutes(120);

    @Override
    public void applyWeather() {
        int month = Simulation.getInstance().getCurrentTime().getMonthValue() - 1;
        int hour = Simulation.getInstance().getCurrentTime().getHour();

        Street.getInstance().setTemperature(stats[TEMPERATURE][month][hour] + new Random().nextInt(3, 6));
        Street.getInstance().setHumidity(stats[HUMIDITY][month][hour] + new Random().nextInt(-7, -3));
        Street.getInstance().setBrightness(stats[BRIGHTNESS][month][hour] + new Random().nextInt(5, 12));
    }

    @Override
    public Duration getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Sunny weather";
    }
}
