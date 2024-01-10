package place.weather;

import place.Street;
import smarthome.Simulation;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static utils.Constants.Time.HOURS;
import static utils.Constants.Time.MONTHS;

public abstract class Weather {
    public static double[][][] stats = new double[3][MONTHS][HOURS];
    public static final List<Weather> weathers = List.of(new NormalWeather(), new SunnyWeather(), new CloudyWeather(), new RainyWeather(), new WindyWeather());
    private static final Map<Weather, Map<Weather, Double>> probabilities = new HashMap<>();
    protected static Street street = Street.getInstance();

    static {
        List<List<Double>> tmp = List.of(List.of(0.3, 0.2, 0.2, 0.1, 0.2),  // Probabilities for NormalWeather
                                         List.of(0.2, 0.5, 0.2, 0.0, 0.1),  // Probabilities for SunnyWeather
                                         List.of(0.2, 0.2, 0.3, 0.3, 0.0),  // Probabilities for CloudyWeather
                                         List.of(0.1, 0.1, 0.3, 0.3, 0.2),  // Probabilities for RainyWeather
                                         List.of(0.3, 0.1, 0.1, 0.2, 0.3)); // Probabilities for WindyWeather

        tmp.forEach(list -> {
            int i = tmp.indexOf(list);
            Map<Weather, Double> map = new HashMap<>();
            list.forEach(elem -> {
                int j = list.indexOf(elem);
                map.put(weathers.get(j), tmp.get(i).get(j));
            });
            probabilities.put(weathers.get(i), map);
        });
    }

    public void changeWeather() {
        double choice = Math.random();
        double accumulator = 0.0;

        for (Map.Entry<Weather, Double> next : probabilities.get(street.getWeather()).entrySet()) {
            accumulator += next.getValue();
            if (choice <= accumulator) {
                street.setWeather(next.getKey());
                street.setWeatherChange(Simulation.getInstance().getCurrentTime().plus(next.getKey().getDuration()));
                applyWeather();
                break;
            }
        }
    }

    public abstract void applyWeather();

    public abstract Duration getDuration();
}
