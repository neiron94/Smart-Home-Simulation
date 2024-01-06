package place;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public enum Weather {
    NORMAL("Normal",
            60,
            new int[]{0, 1},
            new int[]{0, 1},
            new int[]{0, 1},
            new double[]{0.3, 0.2, 0.2, 0.1, 0.2}),
    SUNNY("Sunny",
            120,
            new int[]{3, 6},
            new int[]{-7, -3},
            new int[]{5, 12},
            new double[]{0.2, 0.5, 0.2, 0, 0.1}),
    CLOUDY("Cloudy",
            180,
            new int[]{0, 1},
            new int[]{3, 6},
            new int[]{-9, -4},
            new double[]{0.2, 0.2, 0.3, 0.3, 0}),
    RAINY("Rainy",
            90,
            new int[]{-5, -2},
            new int[]{7, 13},
            new int[]{-17, -12},
            new double[]{0.1, 0.1, 0.3, 0.3, 0.2}),
    WINDY("Windy",
            60,
            new int[]{-7, -4},
            new int[]{-4, -1},
            new int[]{0, 1},
            new double[]{0.3, 0.1, 0.1, 0.2, 0.3});

    static {
        for (Weather weather : Weather.values()) {
            for (int i = 0; i < Weather.values().length; ++i) {
                for (Weather next : Weather.values()) weather.probability.put(next.ordinal(), weather.probabilities[i]);
            }
        }
    }

    private final String description;
    private final Duration duration;
    private final int[] temperatureEffect;
    private final int[] humidityEffect;
    private final int[] brightnessEffect;
    private final double[] probabilities;
    private final Map<Integer, Double> probability;


    Weather(String description, int duration, int[] temperatureEffect, int[] humidityEffect, int[] brightnessEffect, double[] probabilities) {
        this.description = description;
        this.temperatureEffect = temperatureEffect;
        this.humidityEffect = humidityEffect;
        this.brightnessEffect = brightnessEffect;
        this.duration = Duration.ofMinutes(duration);
        this.probabilities = probabilities;
        this.probability = new HashMap<>();
    }

    public Duration getDuration() {
        return duration;
    }

    public Map<Integer, Double> getProbability() {
        return probability;
    }

    public int getTemperatureEffect() {
        return new Random().nextInt(temperatureEffect[0], temperatureEffect[1]);
    }

    public int getHumidityEffect() {
        return new Random().nextInt(humidityEffect[0], humidityEffect[1]);
    }

    public int getBrightnessEffect() {
        return new Random().nextInt(brightnessEffect[0], brightnessEffect[1]);
    }

    @Override
    public String toString() {
        return String.format("%s weather", description);
    }
}
