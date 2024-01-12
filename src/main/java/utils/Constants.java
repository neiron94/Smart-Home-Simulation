package utils;

import java.time.Duration;

/**
 * Class for holding constants.
 */
public final class Constants {

    /**
     * Creature constants.
     */
    public static final class Creature {

        /**
         * Amount of hunger increasing every tick.
         */
        public static final double HUNGER_INCREASE = 0.01;

        /**
         * Amount of fullness increasing every tick.
         */
        public static final double FULLNESS_INCREASE = 0.05;

        /**
         * Amount of hunger after which creature will decide to eat.
         */
        public static final double HUNGER_THRESHOLD = 10;

        /**
         * Amount of fullness after which creature will decide to go to toilet.
         */
        public static final double FULLNESS_THRESHOLD = 30;

        /**
         * Amount of hunger decreasing after eating.
         */
        public static final double EAT_HUNGER_DECREASE = HUNGER_THRESHOLD;

        /**
         * Amount of fullness increasing after eating.
         */
        public static final double EAT_FULLNESS_INCREASE = FULLNESS_THRESHOLD * 1 / 4;
    }

    /**
     * Probability constants.
     */
    public static final class Probabilities {

        /**
         * Probability of disaster event in a simulation tick.
         */
        public static final double DEVICE_EVENT_PROBABILITY = 0.0001;

        /**
         * Describes dependence between disaster event probability and durability
         * of device. Less durability -> more probability. More this constant -> more dependence.
         */
        public static final double EVENT_DURABILITY_DEPENDENCY = 50;

        /**
         * Probability of shutdown of supply system in a simulation tick.
         */
        public static final double SHUTDOWN_PROBABILITY = 0.005;
    }

    /**
     * Degradation of items.
     */
    public static final class Degradation {

        /**
         * Device durability degradation every simulation tick.
         */
        public static final int TIME_DEGRADATION = 1;

        /**
         * Device durability degradation every simulation tick if device status is ON.
         */
        public static final int USE_DEGRADATION = 5;

        /**
         * Device durability degradation if device is damaged by pet.
         */
        public static final int DAMAGE_DEGRADATION = 20;

        /**
         * Device filter status degradation every simulation tick if device status is ON.
         */
        public static final double FILTER_DEGRADATION = 0.05;
    }

    /**
     * Configuration reader constants.
     */
    public static final class ConfigurationReaderConstants {

        /**
         * Index of temperature table in weather stats array.
         */
        public static final int TEMPERATURE = 0;

        /**
         * Index of humidity table in weather stats array.
         */
        public static final int HUMIDITY = 1;

        /**
         * Index of brightness table in weather stats array.
         */
        public static final int BRIGHTNESS = 2;

        /**
         * Index of red item in room configuration config file.
         */
        public static final int RED = 0;

        /**
         * Index of green item in room configuration config file.
         */
        public static final int GREEN = 1;

        /**
         * Index of blue item in room configuration config file.
         */
        public static final int BLUE = 2;
    }

    /**
     * Time constants.
     */
    public static final class Time {

        /**
         * Duration of one simulation tick in hours.
         */
        public static final double TICK_DURATION = 1.0 * 1 / 60; // 1 minute in hours

        /**
         * Amount of months in a year.
         */
        public static final int MONTHS = 12;

        /**
         * Amount of days in a week.
         */
        public static final int DAYS_IN_WEEK = 7;

        /**
         * Average amount of days in a month.
         */
        public static final int DAYS_IN_MONTH = 30;

        /**
         * Amount of hours in a day.
         */
        public static final int HOURS = 24;
    }

    /**
     * Constants for max possible change of room attributes by parameter devices on their max power.
     */
    public static final class ParameterDevices {

        /**
         * Increase of room temperature (in ºC) if heater on max power.
         */
        public static final double HEATER_ON_MAX_POWER = 15;

        /**
         * Decrease of room temperature (in ºC) if AC on max power.
         */
        public static final double AC_ON_MAX_POWER = 15;

        /**
         * Increase of room humidity (in %) if air humidifier on max power.
         */
        public static final double HUMIDIFIER_ON_MAX_POWER = 20;

        /**
         * Decrease of room humidity (in %) if air dryer on max power.
         */
        public static final double DRYER_ON_MAX_POWER = 20;

        /**
         * Increase of room brightness (in %) if light on max power.
         */
        public static final double LIGHT_ON_MAX_POWER = 70;

        /**
         * Decrease of room brightness (in %) if window on max power.
         */
        public static final double WINDOW_ON_MAX_POWER = 70;
    }

    /**
     * House constants.
     */
    public static final class House {

        /**
         * Multiplier which applies on street temperature when counting pure
         * room temperature (pure means without influence of AC or heater).
         */
        public static final double HEAT_PENETRABILITY = 0.6;

        /**
         * Multiplier which applies on street humidity when counting pure
         * room humidity (pure means without influence of air dryer or air humidifier).
         */
        public static final double HUMIDITY_PENETRABILITY = 0.9;

        /**
         * Multiplier which applies on street brightness when counting pure
         * room brightness (pure means without influence of light or window).
         */
        public static final double LIGHT_PENETRABILITY = 0.8;
    }

    /**
     * Guarantee duration of all types of devices (in months).
     */
    public static final class Guarantee {

        /**
         * Duration of fridge guarantee in months.
         */
        public static final int FRIDGE = 24;

        /**
         * Duration of coffee machine guarantee in months.
         */
        public static final int COFFEE_MACHINE = 12;

        /**
         * Duration of toaster guarantee in months.
         */
        public static final int TOASTER = 12;

        /**
         * Duration of microwave guarantee in months.
         */
        public static final int MICROWAVE = 18;

        /**
         * Duration of electric oven guarantee in months.
         */
        public static final int ELECTRIC_OVEN = 36;

        /**
         * Duration of gas oven guarantee in months.
         */
        public static final int GAS_OVEN = 36;

        /**
         * Duration of vent guarantee in months.
         */
        public static final int VENT = 12;

        /**
         * Duration of feeder guarantee in months.
         */
        public static final int FEEDER = 6;

        /**
         * Duration of dishwasher guarantee in months.
         */
        public static final int DISHWASHER = 24;

        /**
         * Duration of washer guarantee in months.
         */
        public static final int WASHER = 24;

        /**
         * Duration of dryer guarantee in months.
         */
        public static final int DRYER = 24;

        /**
         * Duration of water tap guarantee in months.
         */
        public static final int WATER_TAP = 12;

        /**
         * Duration of WC guarantee in months.
         */
        public static final int WC = 12;

        /**
         * Duration of alarm clock guarantee in months.
         */
        public static final int ALARM_CLOCK = 6;

        /**
         * Duration of gaming console guarantee in months.
         */
        public static final int GAMING_CONSOLE = 36;

        /**
         * Duration of stereo system guarantee in months.
         */
        public static final int STEREO_SYSTEM = 36;

        /**
         * Duration of TV guarantee in months.
         */
        public static final int TV = 36;

        /**
         * Duration of AC guarantee in months.
         */
        public static final int AC = 36;

        /**
         * Duration of heater guarantee in months.
         */
        public static final int HEATER = 36;

        /**
         * Duration of air dryer guarantee in months.
         */
        public static final int AIR_DRYER = 24;

        /**
         * Duration of air humidifier guarantee in months.
         */
        public static final int AIR_HUMIDIFIER = 24;

        /**
         * Duration of light guarantee in months.
         */
        public static final int LIGHT = 6;

        /**
         * Duration of window guarantee in months.
         */
        public static final int WINDOW = 24;

        /**
         * Duration of fire alarm guarantee in months.
         */
        public static final int FIRE_ALARM = 24;

        /**
         * Duration of flood alarm guarantee in months.
         */
        public static final int FLOOD_ALARM = 24;

        /**
         * Duration of gas alarm guarantee in months.
         */
        public static final int GAS_ALARM = 24;
    }

    /**
     * Coffee constants.
     */
    public static final class Coffee {

        /**
         * Necessary amount of coffee for all types of coffee (in grams).
         */
        public static final int COFFEE = 18;

        /**
         * Necessary amount of water for espresso (in milliliters).
         */
        public static final int ESPRESSO_WATER = 30;

        /**
         * Necessary amount of milk for espresso (in milliliters).
         */
        public static final int ESPRESSO_MILK = 0;

        /**
         * Necessary amount of water for americano (in milliliters).
         */
        public static final int AMERICANO_WATER = 120;

        /**
         * Necessary amount of milk for americano (in milliliters).
         */
        public static final int AMERICANO_MILK = 0;

        /**
         * Necessary amount of water for cappuccino (in milliliters).
         */
        public static final int CAPPUCCINO_WATER = 60;

        /**
         * Necessary amount of milk for cappuccino (in milliliters).
         */
        public static final int CAPPUCCINO_MILK = 60;

        /**
         * Necessary amount of water for mochaccino (in milliliters).
         */
        public static final int MOCHACCINO_WATER = 60;

        /**
         * Necessary amount of milk for mochaccino (in milliliters).
         */
        public static final int MOCHACCINO_MILK = 60;

        /**
         * Necessary amount of water for latte (in milliliters).
         */
        public static final int LATTE_WATER = 120;

        /**
         * Necessary amount of milk for latte (in milliliters).
         */
        public static final int LATTE_MILK = 120;

        /**
         * Necessary amount of water for irish cream (in milliliters).
         */
        public static final int IRISH_CREAM_WATER = 120;

        /**
         * Necessary amount of milk for irish cream (in milliliters).
         */
        public static final int IRISH_CREAM_MILK = 60;

        /**
         * Necessary amount of water for black coffee (in milliliters).
         */
        public static final int BLACK_COFFEE_WATER = 180;

        /**
         * Necessary amount of milk for black coffee (in milliliters).
         */
        public static final int BLACK_COFFEE_MILK = 0;
    }

    /**
     * Constants for work duration of different programs of devices.
     */
    public static final class WorkDuration {

        /**
         * Duration of light program of dishwasher.
         */
        public static final Duration DISHWASHER_LIGHT = Duration.ofMinutes(30);

        /**
         * Duration of medium program of dishwasher.
         */
        public static final Duration DISHWASHER_MEDIUM = Duration.ofMinutes(60);

        /**
         * Duration of heavy program of dishwasher.
         */
        public static final Duration DISHWASHER_HEAVY = Duration.ofMinutes(120);

        /**
         * Duration of cold program of dryer.
         */
        public static final Duration DRYER_COLD = Duration.ofMinutes(30);

        /**
         * Duration of normal program of dryer.
         */
        public static final Duration DRYER_NORMAL = Duration.ofMinutes(60);

        /**
         * Duration of hot program of dryer.
         */
        public static final Duration DRYER_HOT = Duration.ofMinutes(90);

        /**
         * Duration of sandwich program of toaster.
         */
        public static final Duration TOASTER_SANDWICH = Duration.ofMinutes(1);

        /**
         * Duration of golden program of toaster.
         */
        public static final Duration TOASTER_GOLDEN = Duration.ofMinutes(2);

        /**
         * Duration of crust program of toaster.
         */
        public static final Duration TOASTER_CRUST = Duration.ofMinutes(3);

        /**
         * Duration of delicate program of washer.
         */
        public static final Duration WASHER_DELICATE = Duration.ofMinutes(30);

        /**
         * Duration of normal program of washer.
         */
        public static final Duration WASHER_NORMAL = Duration.ofMinutes(60);

        /**
         * Duration of intensive program of washer.
         */
        public static final Duration WASHER_INTENSIVE = Duration.ofMinutes(90);
    }

    /**
     * Consumption constants (cost of resources and consumption of different devices).
     */
    public static final class Consumption {

        /**
         * Multiplier which applies to device in ON status consumption when it's being counted.
         */
        public static final double ON_MULTIPLIER = 1;

        /**
         * Multiplier which applies to device in OFF status consumption when it's being counted.
         */
        public static final double OFF_MULTIPLIER = 0;

        /**
         * Multiplier which applies to device in STANDBY status consumption when it's being counted.
         */
        public static final double STANDBY_MULTIPLIER = 0.01;

        /**
         * Cost for 1 kilowatt-hour (kWh) in USD.
         */
        public static final double ELECTRICITY_COST = 0.3;

        /**
         * Cost for 1 cubic meter(m³) of gas in USD.
         */
        public static final double GAS_COST = 0.6;

        /**
         * Cost for 1 liter(l) of water in USD.
         */
        public static final double WATER_COST = 0.01;

        /**
         * Power of electric devices in kilowatts (kW).
         */
        public static final class Electricity {

            /**
             * Power of alarm clock in kilowatts (kW).
             */
            public static final double ALARM_CLOCK = 0.005;

            /**
             * Power of coffee machine in kilowatts (kW).
             */
            public static final double COFFEE_MACHINE = 1.0;

            /**
             * Power of dishwasher on light program in kilowatts (kW).
             */
            public static final double DISHWASHER_LIGHT = 1.0;

            /**
             * Power of dishwasher on medium program in kilowatts (kW).
             */
            public static final double DISHWASHER_MEDIUM = 1.5;

            /**
             * Power of dishwasher on heavy program in kilowatts (kW).
             */
            public static final double DISHWASHER_HEAVY = 2;

            /**
             * Power of dryer on cold program in kilowatts (kW).
             */
            public static final double DRYER_COLD = 2.0;

            /**
             * Power of dryer on normal program in kilowatts (kW).
             */
            public static final double DRYER_NORMAL = 2.5;

            /**
             * Power of dryer on hot program in kilowatts (kW).
             */
            public static final double DRYER_HOT = 3;

            /**
             * Power of electric oven on max temperature in kilowatts (kW).
             */
            public static final double OVEN = 5.0;

            /**
             * Power of feeder in kilowatts (kW).
             */
            public static final double FEEDER = 0.01;

            /**
             * Power of fridge on min temperature in kilowatts (kW).
             */
            public static final double FRIDGE = 0.1;

            /**
             * Power of gaming console in kilowatts (kW).
             */
            public static final double GAMING_CONSOLE = 0.15;

            /**
             * Max power of microwave in kilowatts (kW).
             */
            public static final double MICROWAVE = 1.2;

            /**
             * Power of stereo system on max volume in kilowatts (kW).
             */
            public static final double STEREO_SYSTEM = 0.3;

            /**
             * Power of toaster on sandwich program in kilowatts (kW).
             */
            public static final double TOASTER_SANDWICH = 0.8;

            /**
             * Power of toaster on golden program in kilowatts (kW).
             */
            public static final double TOASTER_GOLDEN = 1.0;

            /**
             * Power of toaster on crust program in kilowatts (kW).
             */
            public static final double TOASTER_CRUST = 1.2;

            /**
             * Power of TV on max volume and brightness in kilowatts (kW).
             */
            public static final double TV = 0.1;

            /**
             * Power of vent on slow program in kilowatts (kW).
             */
            public static final double VENT_SLOW = 0.1;

            /**
             * Power of vent on normal program in kilowatts (kW).
             */
            public static final double VENT_NORMAL = 0.2;

            /**
             * Power of vent on turbo program in kilowatts (kW).
             */
            public static final double VENT_TURBO = 0.3;

            /**
             * Power of washer on delicate program in kilowatts (kW).
             */
            public static final double WASHER_DELICATE = 1.0;

            /**
             * Power of washer on normal program in kilowatts (kW).
             */
            public static final double WASHER_NORMAL = 1.5;

            /**
             * Power of washer on intensive program in kilowatts (kW).
             */
            public static final double WASHER_INTENSIVE = 2.0;

            /**
             * Power of water tap on max temperature in kilowatts (kW).
             */
            public static final double WATER_TAP = 3.0;

            /**
             * Max power of AC in kilowatts (kW).
             */
            public static final double AC = 3.5;

            /**
             * Max power of air dryer in kilowatts (kW).
             */
            public static final double AIR_DRYER = 1.8;

            /**
             * Max power of air humidifier in kilowatts (kW).
             */
            public static final double AIR_HUMIDIFIER = 0.5;

            /**
             * Power of alarm in kilowatts (kW).
             */
            public static final double ALARM = 0.005;

            /**
             * Max power of heater in kilowatts (kW).
             */
            public static final double HEATER = 2.0;

            /**
             * Max power of light in kilowatts (kW).
             */
            public static final double LIGHT = 0.05;

            /**
             * Max power of window in kilowatts (kW).
             */
            public static final double WINDOW = 0.05;
        }

        /**
         * Water power (water consumption) of water devices
         * in liters per hour (except for WC, it's in liters per flush).
         */
        public static final class Water {

            /**
             * Water consumption of dishwasher on light program (in liters per hour).
             */
            public static final double DISHWASHER_LIGHT = 13.0;

            /**
             * Water consumption of dishwasher on medium program (in liters per hour).
             */
            public static final double DISHWASHER_MEDIUM = 15.0;

            /**
             * Water consumption of dishwasher on heavy program (in liters per hour).
             */
            public static final double DISHWASHER_HEAVY = 17.0;

            /**
             * Water consumption of washer on delicate program (in liters per hour).
             */
            public static final double WASHER_DELICATE = 40.0;

            /**
             * Water consumption of washer on normal program (in liters per hour).
             */
            public static final double WASHER_NORMAL = 50.0;

            /**
             * Water consumption of washer on intensive program (in liters per hour).
             */
            public static final double WASHER_INTENSIVE = 55.0;

            /**
             * Water consumption of water tap with max openness (in liters per hour).
             */
            public static final double WATER_TAP = 20.0;

            /**
             * Water consumption of WC small flush (in liters per flush).
             */
            public static final double WC_SMALL = 5.0;

            /**
             * Water consumption of WC big flush (in liters per flush).
             */
            public static final double WC_BIG = 7.0;
        }

        /**
         * Gas power (gas consumption) of gas devices in cubic meters (m³) per hour.
         */
        public static final class Gas {

            /**
             * Gas consumption of gas oven on max temperature (in cubic meters (m³) per hour).
             */
            public static final double OVEN = 0.5;
        }
    }
}
