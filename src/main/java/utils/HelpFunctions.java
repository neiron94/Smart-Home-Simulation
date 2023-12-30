package utils;

import consumer.device.Device;
import consumer.device.DeviceStatus;

public class HelpFunctions {
    public static int adjustPercent(int value) {
        return value < 0 ? 0 : Math.min(value, 100);
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
