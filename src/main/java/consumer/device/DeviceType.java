/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer.device;

import java.time.Period;
import consumer.device.common.*;
import consumer.device.sensored.*;
import utils.Constants.Guarantee;

/**
 * Represents every possible concrete device, stores useful information such as
 * start status guarantee duration and repair difficulty.
 */
public enum DeviceType {
    FRIDGE("Fridge", Fridge.class, DeviceStatus.ON, RepairDifficulty.MEDIUM, Guarantee.FRIDGE),
    COFFEE_MACHINE("CoffeeMachine", CoffeeMachine.class, DeviceStatus.OFF, RepairDifficulty.MEDIUM, Guarantee.COFFEE_MACHINE),
    TOASTER("Toaster", Toaster.class, DeviceStatus.STANDBY, RepairDifficulty.EASY, Guarantee.TOASTER),
    MICROWAVE("Microwave", Microwave.class, DeviceStatus.STANDBY, RepairDifficulty.MEDIUM, Guarantee.MICROWAVE),
    ELECTRIC_OVEN("ElectricOven", ElectricOven.class, DeviceStatus.STANDBY, RepairDifficulty.HARD, Guarantee.ELECTRIC_OVEN),
    GAS_OVEN("GasOven", GasOven.class, DeviceStatus.OFF, RepairDifficulty.HARD, Guarantee.GAS_OVEN),
    VENT("Vent", Vent.class, DeviceStatus.STANDBY, RepairDifficulty.EASY, Guarantee.VENT),
    FEEDER("Feeder", Feeder.class, DeviceStatus.ON, RepairDifficulty.EASY, Guarantee.FEEDER),
    DISHWASHER("Dishwasher", Dishwasher.class, DeviceStatus.STANDBY, RepairDifficulty.MEDIUM, Guarantee.DISHWASHER),
    WASHER("Washer", Washer.class, DeviceStatus.STANDBY, RepairDifficulty.MEDIUM, Guarantee.WASHER),
    DRYER("Dryer", Dryer.class, DeviceStatus.STANDBY, RepairDifficulty.MEDIUM, Guarantee.DRYER),
    WATER_TAP("WaterTap", WaterTap.class, DeviceStatus.OFF, RepairDifficulty.EASY, Guarantee.WATER_TAP),
    WC("WC", WC.class, DeviceStatus.OFF, RepairDifficulty.EASY, Guarantee.WC),
    ALARM_CLOCK("AlarmClock", AlarmClock.class, DeviceStatus.STANDBY, RepairDifficulty.MEDIUM, Guarantee.ALARM_CLOCK),
    GAMING_CONSOLE("GamingConsole", GamingConsole.class, DeviceStatus.STANDBY, RepairDifficulty.HARD, Guarantee.GAMING_CONSOLE),
    STEREO_SYSTEM("StereoSystem", StereoSystem.class, DeviceStatus.STANDBY, RepairDifficulty.HARD, Guarantee.STEREO_SYSTEM),
    TV("TV", TV.class, DeviceStatus.STANDBY, RepairDifficulty.HARD, Guarantee.TV),
    AC("AC", AC.class, DeviceStatus.ON, RepairDifficulty.HARD, Guarantee.AC),
    HEATER("Heater", Heater.class, DeviceStatus.ON, RepairDifficulty.HARD, Guarantee.HEATER),
    AIR_DRYER("AirDryer", AirDryer.class, DeviceStatus.ON, RepairDifficulty.MEDIUM, Guarantee.AIR_DRYER),
    AIR_HUMIDIFIER("AirHumidifier", AirHumidifier.class, DeviceStatus.ON, RepairDifficulty.MEDIUM, Guarantee.AIR_HUMIDIFIER),
    LIGHT("Light", Light.class, DeviceStatus.ON, RepairDifficulty.EASY, Guarantee.LIGHT),
    WINDOW("Window", Window.class, DeviceStatus.ON, RepairDifficulty.MEDIUM, Guarantee.WINDOW),
    FIRE_ALARM("FireAlarm", FireAlarm.class, DeviceStatus.STANDBY, RepairDifficulty.MEDIUM, Guarantee.FIRE_ALARM),
    FLOOD_ALARM("FloodAlarm", FloodAlarm.class, DeviceStatus.STANDBY, RepairDifficulty.MEDIUM, Guarantee.FLOOD_ALARM),
    GAS_ALARM("GasAlarm", GasAlarm.class, DeviceStatus.STANDBY, RepairDifficulty.MEDIUM, Guarantee.GAS_ALARM);

    private final String name;
    private final Class<? extends Device> deviceClass;
    private final DeviceStatus startStatus;
    private final RepairDifficulty difficulty;
    private final Period guarantee;

    DeviceType(String name, Class<? extends Device> deviceClass, DeviceStatus status, RepairDifficulty difficulty, int guarantee) {
        this.name = name;
        this.deviceClass = deviceClass;
        this.startStatus = status;
        this.difficulty = difficulty;
        this.guarantee = Period.ofMonths(guarantee);
    }

    public String getName() {
        return name;
    }

    public Class<? extends Device> getDeviceClass() {
        return deviceClass;
    }

    public DeviceStatus getStartStatus() {
        return startStatus;
    }

    public Period getGuarantee() {
        return guarantee;
    }

    public RepairDifficulty getDifficulty() {
        return difficulty;
    }
}
