package utils;

import java.time.LocalTime;

public final class Constants {
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
        public static final LocalTime DISHWASHER_LIGHT = LocalTime.of(0,30);
        public static final LocalTime DISHWASHER_MEDIUM = LocalTime.of(1,0);
        public static final LocalTime DISHWASHER_HEAVY = LocalTime.of(2,0);
        public static final LocalTime DRYER_COLD = LocalTime.of(0,30);
        public static final LocalTime DRYER_NORMAL = LocalTime.of(1,0);
        public static final LocalTime DRYER_HOT = LocalTime.of(1,30);
        public static final LocalTime TOASTER_SANDWICH = LocalTime.of(0,1);
        public static final LocalTime TOASTER_GOLDEN = LocalTime.of(0,2);
        public static final LocalTime TOASTER_CRUST = LocalTime.of(0,3);
        public static final LocalTime WASHER_DELICATE = LocalTime.of(0,30);
        public static final LocalTime WASHER_NORMAL = LocalTime.of(1,0);
        public static final LocalTime WASHER_INTENSIVE = LocalTime.of(1,30);
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
