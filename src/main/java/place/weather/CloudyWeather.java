package place.weather;

import smarthome.Simulation;
import utils.Constants;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CloudyWeather implements Weather  {
    private final Duration duration = Duration.ofMinutes(180);
    private final Map<Weather, Double> probabilities = new HashMap<>();

    public CloudyWeather() {
        double[] probability = new double[]{0.2, 0.2, 0.3, 0.3, 0};
        for (int i = 0; i < weathers.size(); ++i) probabilities.put(weathers.get(i), probability[i]);
    }

    @Override
    public void applyWeather() {
        int month = Simulation.getInstance().getCurrentTime().getMonthValue() - 1;
        int hour = Simulation.getInstance().getCurrentTime().getHour();

        street.setTemperature(stats[Constants.WeatherConstants.TEMPERATURE][month][hour] + new Random().nextInt(0, 1));
        street.setHumidity(stats[Constants.WeatherConstants.HUMIDITY][month][hour] + new Random().nextInt(3, 6));
        street.setBrightness(stats[Constants.WeatherConstants.BRIGHTNESS][month][hour] + new Random().nextInt(-9, -4));
    }

    @Override
    public Map<Weather, Double> getProbabilities() {
        return probabilities;
    }

    @Override
    public Duration getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Cloudy weather";
    }
}
