package utils;

import java.time.Duration;

public final class Constants {

    public static final class Probabilities {
        public static final double DEVICE_EVENT_PROBABILITY = 0.0001;
        public static final double EVENT_DURABILITY_DEPENDENCY = 50;
    }

    public static final class Degradation {
        public static final int TIME_DEGRADATION = 1;
        public static final int USE_DEGRADATION = 5;
        public static final int DAMAGE_DEGRADATION = 20;
        public static final double FILTER_DEGRADATION = 0.05;
    }

    public static final class WeatherConstants {
        public static final int TEMPERATURE = 0;
        public static final int HUMIDITY = 1;
        public static final int BRIGHTNESS = 2;
    }

    public static final class ConfigurationReaderConstants {
        public static final int TEMPERATURE = 0;
        public static final int HUMIDITY = 1;
        public static final int BRIGHTNESS = 2;
        public static final int RED = 0;
        public static final int GREEN = 1;
        public static final int BLUE = 2;
    }

    public static final class Time {
        public static final double TICK_DURATION = 1.0 * 1 / 60; // 1 minute in hours
        public static final int MONTHS = 12;
        public static final int HOURS = 12;

    }

    public static final class ParameterDevices {
        public static final double HEATER_ON_MAX_POWER = 15;
        public static final double AC_ON_MAX_POWER = 15;
        public static final double HUMIDIFIER_ON_MAX_POWER = 20;
        public static final double DRYER_ON_MAX_POWER = 20;
        public static final double LIGHT_ON_MAX_POWER = 70;
        public static final double WINDOW_ON_MAX_POWER = 70;
    }

    public static final class House {
        public static final double HEAT_PENETRABILITY = 0.6;
        public static final double HUMIDITY_PENETRABILITY = 0.9;
        public static final double LIGHT_PENETRABILITY = 0.8;
    }

    public static final class Guarantee {
        // All guarantee durations in months
        public static final int FRIDGE = 24;
        public static final int COFFEE_MACHINE = 12;
        public static final int TOASTER = 12;
        public static final int MICROWAVE = 18;
        public static final int ELECTRIC_OVEN = 36;
        public static final int GAS_OVEN = 36;
        public static final int VENT = 12;
        public static final int FEEDER = 6;
        public static final int DISHWASHER = 24;
        public static final int WASHER = 24;
        public static final int DRYER = 24;
        public static final int WATER_TAP = 12;
        public static final int WC = 12;
        public static final int ALARM_CLOCK = 6;
        public static final int GAMING_CONSOLE = 36;
        public static final int STEREO_SYSTEM = 36;
        public static final int TV = 36;
        public static final int AC = 36;
        public static final int HEATER = 36;
        public static final int AIR_DRYER = 24;
        public static final int AIR_HUMIDIFIER = 24;
        public static final int LIGHT = 6;
        public static final int WINDOW = 24;
        public static final int FIRE_ALARM = 24;
        public static final int FLOOD_ALARM = 24;
        public static final int GAS_ALARM = 24;
    }

    public static final class Coffee {
        // Water in milliliters (mL), milk in milliliters (mL), coffee in grams (g)
        public static final int COFFEE = 18;

        public static final int ESPRESSO_WATER = 30;
        public static final int ESPRESSO_MILK = 0;
        public static final int AMERICANO_WATER = 120;
        public static final int AMERICANO_MILK = 0;
        public static final int CAPPUCCINO_WATER = 60;
        public static final int CAPPUCCINO_MILK = 60;
        public static final int MOCHACCINO_WATER = 60;
        public static final int MOCHACCINO_MILK = 60;
        public static final int LATTE_WATER = 120;
        public static final int LATTE_MILK = 120;
        public static final int IRISH_CREAM_WATER = 120;
        public static final int IRISH_CREAM_MILK = 60;
        public static final int BLACK_COFFEE_WATER = 180;
        public static final int BLACK_COFFEE_MILK = 0;
    }

    public static final class WorkTime {
        public static final Duration DISHWASHER_LIGHT = Duration.ofMinutes(30);
        public static final Duration DISHWASHER_MEDIUM = Duration.ofMinutes(60);
        public static final Duration DISHWASHER_HEAVY = Duration.ofMinutes(120);
        public static final Duration DRYER_COLD = Duration.ofMinutes(30);
        public static final Duration DRYER_NORMAL = Duration.ofMinutes(60);
        public static final Duration DRYER_HOT = Duration.ofMinutes(90);
        public static final Duration TOASTER_SANDWICH = Duration.ofMinutes(1);
        public static final Duration TOASTER_GOLDEN = Duration.ofMinutes(2);
        public static final Duration TOASTER_CRUST = Duration.ofMinutes(3);
        public static final Duration WASHER_DELICATE = Duration.ofMinutes(30);
        public static final Duration WASHER_NORMAL = Duration.ofMinutes(60);
        public static final Duration WASHER_INTENSIVE = Duration.ofMinutes(90);
    }

    public static final class Consumption {
        public static final double ON_MULTIPLIER = 1;
        public static final double OFF_MULTIPLIER = 0;
        public static final double STANDBY_MULTIPLIER = 0.01;

        public static final double ELECTRICITY_COST = 0.3;   // cost for 1 kilowatt-hour(kWh) in USD
        public static final double GAS_COST = 0.6;   // cost for 1 cubic meter(m³) of gas in USD
        public static final double WATER_COST = 0.01;   // cost for 1 liter(l) of water in USD

        public static final class Electricity {
            // All in kilowatts (kW)
            public static final double ALARM_CLOCK = 0.005;
            public static final double COFFEE_MACHINE = 1.0;
            public static final double DISHWASHER_LIGHT = 1.0;
            public static final double DISHWASHER_MEDIUM = 1.5;
            public static final double DISHWASHER_HEAVY = 2;
            public static final double DRYER_COLD = 2.0;
            public static final double DRYER_NORMAL = 2.5;
            public static final double DRYER_HOT = 3;
            public static final double OVEN = 5.0;
            public static final double FEEDER = 0.01;
            public static final double FRIDGE = 0.1;
            public static final double GAMING_CONSOLE = 0.15;
            public static final double MICROWAVE = 1.2;
            public static final double STEREO_SYSTEM = 0.3;
            public static final double TOASTER_SANDWICH = 0.8;
            public static final double TOASTER_GOLDEN = 1.0;
            public static final double TOASTER_CRUST = 1.2;
            public static final double TV = 0.1;
            public static final double VENT_SLOW = 0.1;
            public static final double VENT_NORMAL = 0.2;
            public static final double VENT_TURBO = 0.3;
            public static final double WASHER_DELICATE = 1.0;
            public static final double WASHER_NORMAL = 1.5;
            public static final double WASHER_INTENSIVE = 2.0;
            public static final double WATER_TAP = 3.0;
            public static final double AC = 3.5;
            public static final double AIR_DRYER = 1.8;
            public static final double AIR_HUMIDIFIER = 0.5;
            public static final double ALARM = 0.005;
            public static final double HEATER = 2.0;
            public static final double LIGHT = 0.05;
            public static final double WINDOW = 0.05;
        }

        public static final class Water {
            // All in liters per hour (except for WC, it's in liters per flush)
            public static final double DISHWASHER_LIGHT = 13.0;
            public static final double DISHWASHER_MEDIUM = 15.0;
            public static final double DISHWASHER_HEAVY = 17.0;
            public static final double WASHER_DELICATE = 40.0;
            public static final double WASHER_NORMAL = 50.0;
            public static final double WASHER_INTENSIVE = 55.0;
            public static final double WATER_TAP = 20.0;
            public static final double WC_SMALL = 5.0;
            public static final double WC_BIG = 7.0;
        }

        public static final class Gas {
            // All in cubic meters (m³)
            public static final double OVEN = 0.5;
        }
    }
}
