package utils;

import consumer.device.Device;
import consumer.device.DeviceStatus;

import java.util.List;
import java.util.Random;

public class HelpFunctions {
    public static int adjustPercent(int value) {
        return value < 0 ? 0 : Math.min(value, 100);
    }

    public static double adjustToRange(double value, double min, double max) {
        return value < min ? min : Math.min(value, max);
    }

    public static <T> T getRandomObject(List<T> array) {
        Random random = new Random();
        try {
            int randomIndex = random.nextInt(array.size());
            return array.get(randomIndex);
        } catch (IllegalArgumentException e) {
            return null; // TODO Change return value
        }
    }

    public static double countElectricityConsumption(DeviceStatus status, double power) {
        return status.getMultiplier() * power * Device.TICK_DURATION;   // TODO - take TICK_DURATION from Constants
    }

    public static double countWaterConsumption(DeviceStatus status, double power) {
        return status == DeviceStatus.ON ? power * Device.TICK_DURATION : 0;   // TODO - take TICK_DURATION from Constants
    }

    public static double countGasConsumption(DeviceStatus status, double power) {
        return status == DeviceStatus.ON ? power * Device.TICK_DURATION : 0;   // TODO - take TICK_DURATION from Constants
    }
}
