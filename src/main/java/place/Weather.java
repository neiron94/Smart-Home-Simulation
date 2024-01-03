package place;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public enum Weather {
    NORMAL("Normal",
            60,
            0,
            0,
            0,
            new double[]{0.3, 0.2, 0.2, 0.1, 0.2}),
    SUNNY("Sunny",
            120,
            new Random().nextInt(3,6),
            new Random().nextInt(-7,-3),
            new Random().nextInt(5,12),
            new double[]{0.2, 0.5, 0.2, 0, 0.1}),
    CLOUDY("Cloudy",
            180,
            0,
            new Random().nextInt(3,6),
            new Random().nextInt(-9,-4),
            new double[]{0.2, 0.2, 0.3, 0.3, 0}),
    RAINY("Rainy",
            90,
            new Random().nextInt(-5,-2),
            new Random().nextInt(7,13),
            new Random().nextInt(-17,-12),
            new double[]{0.1, 0.1, 0.3, 0.3, 0.2}),
    WINDY("Windy",
            60,
            new Random().nextInt(-7,-4),
            new Random().nextInt(-4,-1),
            0,
            new double[]{0.3, 0.1, 0.1, 0.2, 0.3});

    private final String description;
    private final Duration duration;
    private final int temperatureEffect;
    private final int humidityEffect;
    private final int brightnessEffect;
    private final Map<Weather, Double> probability = new HashMap<>();

    Weather(String description, int duration, int temperatureEffect, int humidityEffect, int brightnessEffect, double[] probabilities) {
        int i = 0;
        for (Weather weather : Weather.values()) probability.put(weather, probabilities[i++]);

        this.description = description;
        this.temperatureEffect = temperatureEffect;
        this.humidityEffect = humidityEffect;
        this.brightnessEffect = brightnessEffect;
        this.duration = Duration.ofMinutes(duration);
    }

    public Duration getDuration() {
        return duration;
    }

    public Map<Weather, Double> getProbability() {
        return probability;
    }

    public int getTemperatureEffect() {
        return temperatureEffect;
    }

    public int getHumidityEffect() {
        return humidityEffect;
    }

    public int getBrightnessEffect() {
        return brightnessEffect;
    }

    @Override
    public String toString() {
        return String.format("%s weather", description);
    }
}
