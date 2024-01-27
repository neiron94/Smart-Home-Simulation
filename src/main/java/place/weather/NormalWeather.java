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
 * Normal weather.
 */
public class NormalWeather extends Weather {

    /**
     * Creates normal weather.
     */
    public NormalWeather() {
        super(Duration.ofMinutes(60));
    }

    @Override
    public void applyWeather() {
        int month = Simulation.getInstance().getCurrentTime().getMonthValue() - 1;
        int hour = Simulation.getInstance().getCurrentTime().getHour();

        Street.getInstance().setTemperature(stats[TEMPERATURE][month][hour] + new Random().nextInt(0, 1));
        Street.getInstance().setHumidity(stats[HUMIDITY][month][hour] + new Random().nextInt(0, 1));
        Street.getInstance().setBrightness(stats[BRIGHTNESS][month][hour] + new Random().nextInt(0, 1));
    }

    @Override
    public String toString() {
        return "Normal weather";
    }
}
