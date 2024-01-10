package place.weather;

import smarthome.Simulation;
import utils.Constants;
import java.time.Duration;
import java.util.Random;

public class SunnyWeather extends Weather {
    private final Duration duration = Duration.ofMinutes(120);

    @Override
    public void applyWeather() {
        int month = Simulation.getInstance().getCurrentTime().getMonthValue() - 1;
        int hour = Simulation.getInstance().getCurrentTime().getHour();

        street.setTemperature(stats[Constants.WeatherConstants.TEMPERATURE][month][hour] + new Random().nextInt(3, 6));
        street.setHumidity(stats[Constants.WeatherConstants.HUMIDITY][month][hour] + new Random().nextInt(-7, -3));
        street.setBrightness(stats[Constants.WeatherConstants.BRIGHTNESS][month][hour] + new Random().nextInt(5, 12));
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
