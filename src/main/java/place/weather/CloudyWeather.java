/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package place.weather;

import place.Street;
import smarthome.Simulation;
import static utils.Constants.ConfigurationReaderConstants.*;
import java.time.Duration;
import java.util.Random;

/**
 * Cloudy weather.
 */
public class CloudyWeather extends Weather  {

    /**
     * Creates cloudy weather.
     */
    public CloudyWeather() {
        super(Duration.ofMinutes(180));
    }

    @Override
    public void applyWeather() {
        int month = Simulation.getInstance().getCurrentTime().getMonthValue() - 1;
        int hour = Simulation.getInstance().getCurrentTime().getHour();

        Street.getInstance().setTemperature(stats[TEMPERATURE][month][hour] + new Random().nextInt(0, 1));
        Street.getInstance().setHumidity(stats[HUMIDITY][month][hour] + new Random().nextInt(3, 6));
        Street.getInstance().setBrightness(stats[BRIGHTNESS][month][hour] + new Random().nextInt(-9, -4));
    }

    @Override
    public String toString() {
        return "Cloudy weather";
    }
}
