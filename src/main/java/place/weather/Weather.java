package place.weather;

import place.Street;
import smarthome.Simulation;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public interface Weather {
    int TEMPERATURE = 0; // TODO Move to constants
    int HUMIDITY = 1; // TODO Move to constants
    int BRIGHTNESS = 2; // TODO Move to constants

    Street street = Street.getInstance();
    double[][][] stats = new double[3][12][24]; // TODO Put MONTHS=12 and HOURS=24 to constants
    List<Weather> weathers = List.of(new NormalWeather(), new SunnyWeather(), new CloudyWeather(), new RainyWeather(), new WindyWeather());

    default void changeWeather() {
        double choice = Math.random();
        double accumulator = 0.0;

        for (Map.Entry<Weather, Double> entry : street.getWeather().getProbabilities().entrySet()) {
            accumulator += entry.getValue();
            if (choice <= accumulator) {
                street.setWeather(entry.getKey());
                street.setWeatherChange(Simulation.getInstance().getCurrentTime().plus(entry.getKey().getDuration()));
                applyWeather();
                break;
            }
        }
    }

    void applyWeather();

    Map<Weather, Double> getProbabilities();

    Duration getDuration();
}
