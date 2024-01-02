package consumer.device;

import java.time.Period;
import consumer.device.common.*;
import consumer.device.sensored.*;

public enum DeviceType {
    // TODO Check repairDifficulties, guarantee
    FRIDGE("Fridge", Fridge.class, DeviceStatus.ON, RepairDifficulty.MEDIUM, 24),
    COFFEE_MACHINE("CoffeeMachine", CoffeeMachine.class, DeviceStatus.STANDBY, RepairDifficulty.MEDIUM, 12),
    TOASTER("Toaster", Toaster.class, DeviceStatus.STANDBY, RepairDifficulty.EASY, 12),
    MICROWAVE("Microwave", Microwave.class, DeviceStatus.STANDBY, RepairDifficulty.MEDIUM, 18),
    ELECTRIC_OVEN("ElectricOven", ElectricOven.class, DeviceStatus.OFF, RepairDifficulty.HARD, 36),
    GAS_OVEN("GasOven", GasOven.class, DeviceStatus.OFF, RepairDifficulty.HARD, 36),
    VENT("Vent", Vent.class, DeviceStatus.OFF, RepairDifficulty.EASY, 12),
    FEEDER("Feeder", Feeder.class, DeviceStatus.ON, RepairDifficulty.EASY, 6),
    DISHWASHER("Dishwasher", Dishwasher.class, DeviceStatus.STANDBY, RepairDifficulty.MEDIUM, 24),
    WASHER("Washer", Washer.class, DeviceStatus.STANDBY, RepairDifficulty.MEDIUM, 24),
    DRYER("Dryer", Dryer.class, DeviceStatus.STANDBY, RepairDifficulty.MEDIUM, 24),
    WATER_TAP("WaterTap", WaterTap.class, DeviceStatus.OFF, RepairDifficulty.EASY, 12),
    WC("WC", WC.class, DeviceStatus.OFF, RepairDifficulty.EASY, 12),
    ALARM_CLOCK("AlarmClock", AlarmClock.class, DeviceStatus.STANDBY, RepairDifficulty.MEDIUM, 6),
    GAMING_CONSOLE("GamingConsole", GamingConsole.class, DeviceStatus.STANDBY, RepairDifficulty.HARD, 36),
    STEREO_SYSTEM("StereoSystem", StereoSystem.class, DeviceStatus.STANDBY, RepairDifficulty.HARD, 36),
    TV("TV", TV.class, DeviceStatus.STANDBY, RepairDifficulty.HARD, 36),
    AC("AC", AC.class, DeviceStatus.ON, RepairDifficulty.HARD, 36),
    HEATER("Heater", Heater.class, DeviceStatus.ON, RepairDifficulty.HARD, 36),
    AIR_DRYER("AirDryer", AirDryer.class, DeviceStatus.ON, RepairDifficulty.MEDIUM, 24),
    AIR_HUMIDIFIER("AirHumidifier", AirHumidifier.class, DeviceStatus.ON, RepairDifficulty.MEDIUM, 24),
    LIGHT("Light", Light.class, DeviceStatus.ON, RepairDifficulty.EASY, 6),
    WINDOW("Window", Window.class, DeviceStatus.ON, RepairDifficulty.MEDIUM, 24),
    FIRE_ALARM("FireAlarm", FireAlarm.class, DeviceStatus.STANDBY, RepairDifficulty.MEDIUM, 24),
    FLOOD_ALARM("FloodAlarm", FloodAlarm.class, DeviceStatus.STANDBY, RepairDifficulty.MEDIUM, 24),
    GAS_ALARM("GasAlarm", GasAlarm.class, DeviceStatus.STANDBY, RepairDifficulty.MEDIUM, 24);

    private final String name;
    private final Class<?> deviceClass;
    private final DeviceStatus startStatus;
    private final RepairDifficulty difficulty;
    private final Period guarantee;

    DeviceType(String name, Class<?> deviceClass, DeviceStatus status, RepairDifficulty difficulty, int guarantee) {
        this.name = name;
        this.deviceClass = deviceClass;
        this.startStatus = status;
        this.difficulty = difficulty;
        this.guarantee = Period.ofMonths(guarantee);
    }

    public String getName() {
        return name;
    }

    public Class<?> getDeviceClass() {
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
