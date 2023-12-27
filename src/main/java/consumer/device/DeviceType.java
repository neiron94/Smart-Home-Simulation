package consumer.device;

import java.util.Date;

public enum DeviceType {
    // TODO Check repairDifficulties, guarantee
    FRIDGE("Fridge", DeviceStatus.ON, RepairDifficulty.MEDIUM, 24),
    COFFEE_MACHINE("CoffeeMachine", DeviceStatus.OFF, RepairDifficulty.MEDIUM, 12),
    TOASTER("Toaster", DeviceStatus.STANDBY, RepairDifficulty.EASY, 12),
    MICROWAVE("Microwave", DeviceStatus.STANDBY, RepairDifficulty.MEDIUM, 18),
    ELECTRIC_OVEN("ElectricOven", DeviceStatus.STANDBY, RepairDifficulty.HARD, 36),
    GAS_OVEN("GasOven", DeviceStatus.OFF, RepairDifficulty.HARD, 36),
    VENT("Vent", DeviceStatus.STANDBY, RepairDifficulty.EASY, 12),
    FEEDER("Feeder", DeviceStatus.ON, RepairDifficulty.EASY, 6),
    DISHWASHER("Dishwasher", DeviceStatus.STANDBY, RepairDifficulty.MEDIUM, 24),
    WASHER("Washer", DeviceStatus.STANDBY, RepairDifficulty.MEDIUM, 24),
    DRYER("Dryer", DeviceStatus.STANDBY, RepairDifficulty.MEDIUM, 24),
    WATER_TAP("WaterTap", DeviceStatus.OFF, RepairDifficulty.EASY, 12),
    WC("WC", DeviceStatus.OFF, RepairDifficulty.EASY, 12),
    ALARM_CLOCK("AlarmClock", DeviceStatus.STANDBY, RepairDifficulty.MEDIUM, 6),
    GAMING_CONSOLE("GamingConsole", DeviceStatus.STANDBY, RepairDifficulty.HARD, 36),
    STEREO_SYSTEM("StereoSystem", DeviceStatus.STANDBY, RepairDifficulty.HARD, 36),
    TV("TV", DeviceStatus.STANDBY, RepairDifficulty.HARD, 36),
    AC("AC", DeviceStatus.ON, RepairDifficulty.HARD, 36),
    HEATER("Heater", DeviceStatus.ON, RepairDifficulty.HARD, 36),
    AIR_DRYER("AirDryer", DeviceStatus.ON, RepairDifficulty.MEDIUM, 24),
    AIR_HUMIDIFIER("AirHumidifier", DeviceStatus.ON, RepairDifficulty.MEDIUM, 24),
    LIGHT("Light", DeviceStatus.ON, RepairDifficulty.EASY, 6),
    WINDOW("Window", DeviceStatus.ON, RepairDifficulty.MEDIUM, 24),
    FIRE_ALARM("FireAlarm", DeviceStatus.STANDBY, RepairDifficulty.MEDIUM, 24),
    FLOOD_ALARM("FloodAlarm", DeviceStatus.STANDBY, RepairDifficulty.MEDIUM, 24),
    GAS_ALARM("GasAlarm", DeviceStatus.STANDBY, RepairDifficulty.MEDIUM, 24);

    private final String name;
    private final DeviceStatus startStatus;
    private final RepairDifficulty difficulty;
    private final Date guarantee; // TODO Select proper class for store guarantee period (in months)
    private final String manualText;

    DeviceType(String description, DeviceStatus status, RepairDifficulty difficulty, Date guarantee) {
        this.name = description;
        this.startStatus = status;
        this.difficulty = difficulty;
        this.guarantee = guarantee;
        this.manualText = String.format("You are reading %s manual. Difficulty of repair is %s.", description, difficulty);
    }

    public String getName() {
        return name;
    }

    public DeviceStatus getStartStatus() {
        return startStatus;
    }

    public Date getGuarantee() {
        return guarantee;
    }

    public String getManualText() {
        return manualText;
    }

    public RepairDifficulty getDifficulty() {
        return difficulty;
    }
}
