package place.weather;

import smarthome.Simulation;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RainyWeather implements Weather {
    private final Duration duration = Duration.ofMinutes(90);
    private final Map<Weather, Double> probabilities = new HashMap<>();

    public RainyWeather() {
        double[] probability = new double[]{0.1, 0.1, 0.3, 0.3, 0.2};
        for (int i = 0; i < weathers.size(); ++i) probabilities.put(weathers.get(i), probability[i]);
    }

    @Override
    public void applyWeather() {
        int month = Simulation.getInstance().getCurrentTime().getMonthValue() - 1;
        int hour = Simulation.getInstance().getCurrentTime().getHour();

        street.setTemperature(stats[TEMPERATURE][month][hour] + new Random().nextInt(-5, -2));
        street.setHumidity(stats[HUMIDITY][month][hour] + new Random().nextInt(7, 13));
        street.setBrightness(stats[BRIGHTNESS][month][hour] + new Random().nextInt(-17, -12));
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
        return "Rainy weather";
    }
}