package place.weather;

import place.Street;
import smarthome.Simulation;
import utils.ConfigurationReader;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static utils.Constants.Time.HOURS;
import static utils.Constants.Time.MONTHS;

/**
 * Weather affects attributes of street and dynamically changes every hour in {@link Street#routine()}.
 * Every concrete weather has Markov chain table, which sets the probability of transition to another weather state.
 */
public abstract class Weather {
    private final Duration duration;

    /**
     * Preloaded tables of attribute dependency on month of a year and hour of a day.
     */
    public static final double[][][] stats = new double[3][MONTHS][HOURS];
    static {
        ConfigurationReader.readWeatherConfig();
    }

    /**
     * List of all possible weathers, between which will be switched.
     */
    public static final List<Weather> weathers = List.of(new NormalWeather(), new SunnyWeather(), new CloudyWeather(), new RainyWeather(), new WindyWeather());
    private static final Map<Weather, Map<Weather, Double>> probabilities = new HashMap<>();
    static {
        double[][] probability = {
                {0.3, 0.2, 0.2, 0.1, 0.2}, // Probabilities of next weather for Normal weather
                {0.2, 0.5, 0.2, 0.0, 0.1}, // Probabilities of next weather for Sunny weather
                {0.2, 0.2, 0.3, 0.3, 0.0}, // Probabilities of next weather for Cloudy weather
                {0.1, 0.1, 0.3, 0.3, 0.2}, // Probabilities of next weather for Rainy weather
                {0.3, 0.1, 0.1, 0.2, 0.3}  // Probabilities of next weather for Windy weather
        };

        for (int i = 0; i < probability.length; ++i) {
            Map<Weather, Double> map = new HashMap<>();
            for (int j = 0; j < probability[i].length; ++j) map.put(weathers.get(j), probability[i][j]);
            probabilities.put(weathers.get(i), map);
        }
    }

    protected Weather(Duration duration) {
        this.duration = duration;
    }

    /**
     * Switches to another weather, depends on Markov probability table.
     */
    public void changeWeather() {
        double choice = Math.random();
        double accumulator = 0.0;

        for (Map.Entry<Weather, Double> next : probabilities.get(Street.getInstance().getWeather()).entrySet()) {
            accumulator += next.getValue();
            if (choice <= accumulator) {
                Street.getInstance().setWeather(next.getKey());
                Street.getInstance().setWeatherChange(Simulation.getInstance().getCurrentTime().plus(next.getKey().getDuration()));
                applyWeather();
                break;
            }
        }
    }

    /**
     * Changes attributes of street, is called every hour in {@link Street#routine()}.
     */
    public abstract void applyWeather();

    public Duration getDuration() {
        return duration;
    }
}
