package utils;

import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import creature.Creature;
import place.Room;
import place.RoomType;
import smarthome.Simulation;
import utils.exceptions.DeviceNotFoundException;
import utils.exceptions.RoomNotFoundException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelpFunctions {
    public static final Logger logger = LogManager.getLogger();

    public static int adjustPercent(int value) {
        return value < 0 ? 0 : Math.min(value, 100);
    }

    public static <T> Optional<T> getRandomObject(List<T> array) {
        if (array.size() == 0) return Optional.empty();
        return Optional.of(array.get(new Random().nextInt(array.size())));
    }

    public static double adjustPercent(double value) {
        return value < 0 ? 0 : Math.min(value, 100);
    }

    public static double adjustToRange(double value, double min, double max) {
        return value < min ? min : Math.min(value, max);
    }

    public static int adjustToRange(int value, int min, int max) {
        return value < min ? min : Math.min(value, max);
    }

    public static long adjustToRange(long value, long min, long max) {
        return value < min ? min : Math.min(value, max);
    }

    public static double countElectricityConsumption(DeviceStatus status, double power) {
        return status.getMultiplier() * power * Constants.Time.TICK_DURATION;
    }

    public static double countWaterConsumption(DeviceStatus status, double power) {
        return status == DeviceStatus.ON ? power * Constants.Time.TICK_DURATION : 0;
    }

    public static double countGasConsumption(DeviceStatus status, double power) {
        return status == DeviceStatus.ON ? power * Constants.Time.TICK_DURATION : 0;
    }

    public static void makeRecord(Creature creature, String description) {
        creature.getActivity().addActivity(description);
    }

    public static void makeRecord(Creature creature, Device device, String description) {
        creature.getActivity().addActivity(description);
        creature.getActivity().increaseUsage(device);
    }

    public static Device findDevice(DeviceType type) throws DeviceNotFoundException {
        return Simulation.getInstance().getDevices().stream()
                .filter(device -> device.getType() == type)
                .findFirst()
                .orElseThrow(DeviceNotFoundException::new);
    }

    public static Device findDevice(DeviceType type1, DeviceType type2) throws DeviceNotFoundException {
        return Simulation.getInstance().getDevices().stream()
                .filter(device -> device.getType() == type1 || device.getType() == type2)
                .findFirst()
                .orElseThrow(DeviceNotFoundException::new);
    }

    public static Device findDevice(DeviceType type, Room room) throws DeviceNotFoundException {
        return Simulation.getInstance().getDevices().stream()
                .filter(device -> device.getType() == type && device.getRoom() == room)
                .findFirst()
                .orElseThrow(DeviceNotFoundException::new);
    }

    public static Device findDevice(DeviceType type, RoomType roomType) throws DeviceNotFoundException {
        return Simulation.getInstance().getDevices().stream()
                .filter(device -> device.getType() == type && device.getRoom().getType() == roomType)
                .findFirst()
                .orElseThrow(DeviceNotFoundException::new);
    }

    public static Room findRoom(RoomType type) throws RoomNotFoundException {
        return Simulation.getInstance().getHome().getFloors().stream()
                .flatMap(floor -> floor.getRooms().stream())
                .filter(room -> room.getType() == type)
                .findFirst()
                .orElseThrow(RoomNotFoundException::new);
    }

    public static Optional<DayPeriod> getDayPeriod(LocalDateTime date) {
        LocalTime time = date.toLocalTime();
        for (DayPeriod period : DayPeriod.values()) {
            if (!time.isBefore(period.getStart()) && !time.isAfter(period.getEnd())) return Optional.of(period);
        }
        return Optional.empty();
    }
}
