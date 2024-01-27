/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

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
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class for holding help static methods.
 */
public class HelpFunctions {

    /**
     * Instance of logger is located here, so no need to create it in each file.
     */
    public static final Logger logger = LogManager.getLogger();

    /**
     * Finds and returns a random element from the specified list.
     * @param array array of elements
     * @return optional of random element from the list, or null if list is empty
     * @param <T> type of elements in array
     */
    public static <T> Optional<T> getRandomObject(List<T> array) {
        if (array.size() == 0) return Optional.empty();
        return Optional.of(array.get(new Random().nextInt(array.size())));
    }

    /**
     * Adjusts the given integer value to the range [0, 100].
     * @param value the value to adjust
     * @return the adjusted value
     */
    public static int adjustPercent(int value) {
        return value < 0 ? 0 : Math.min(value, 100);
    }

    /**
     * Adjusts the given double value to the range [0.0, 100.0].
     * @param value the value to adjust
     * @return the adjusted value
     */
    public static double adjustPercent(double value) {
        return value < 0 ? 0 : Math.min(value, 100);
    }

    /**
     * Adjusts the given double value to the range [min, max].
     * @param value the value to adjust
     * @param min the lower bound
     * @param max the upper bound
     * @return the adjusted value
     */
    public static double adjustToRange(double value, double min, double max) {
        return value < min ? min : Math.min(value, max);
    }

    /**
     * Adjusts the given integer value to the range [min, max].
     * @param value the value to adjust
     * @param min the lower bound
     * @param max the upper bound
     * @return the adjusted value
     */
    public static int adjustToRange(int value, int min, int max) {
        return value < min ? min : Math.min(value, max);
    }

    /**
     * Adjusts the given long value to the range [min, max].
     * @param value the value to adjust
     * @param min the lower bound
     * @param max the upper bound
     * @return the adjusted value
     */
    public static long adjustToRange(long value, long min, long max) {
        return value < min ? min : Math.min(value, max);
    }

    /**
     * Counts and returns electricity consumption of device depending on its status and power.
     * @param status the status of some device
     * @param power the power of some device
     * @return consumed electricity
     */
    public static double countElectricityConsumption(DeviceStatus status, double power) {
        return status.getMultiplier() * power * Constants.Time.TICK_DURATION;
    }

    /**
     * Counts and returns water consumption of device depending on its status and power.
     * @param status the status of some device
     * @param power the power of some device
     * @return consumed water
     */
    public static double countWaterConsumption(DeviceStatus status, double power) {
        return status == DeviceStatus.ON ? power * Constants.Time.TICK_DURATION : 0;
    }

    /**
     * Counts and returns gas consumption of device depending on its status and power.
     * @param status the status of some device
     * @param power the power of some device
     * @return consumed gas
     */
    public static double countGasConsumption(DeviceStatus status, double power) {
        return status == DeviceStatus.ON ? power * Constants.Time.TICK_DURATION : 0;
    }

    /**
     * Makes a record in creature activity for future report.
     * @param creature creature whose action will be written
     * @param description description of the action
     */
    public static void makeRecord(Creature creature, String description) {
        creature.getActivity().addActivity(String.format("%s - %s", Simulation.getInstance().getCurrentTime().format(DateTimeFormatter.ofPattern("HH:mm")), description));
    }

    /**
     * Makes a record in creature activity for future report, also increment usage of the specified device.
     * @param creature creature whose action will be written
     * @param device device which usage will be increased
     * @param description description of the action
     */
    public static void makeRecord(Creature creature, Device device, String description) {
        makeRecord(creature, description);
        creature.getActivity().increaseUsage(device);
    }

    /**
     * Finds device with the specified type.
     * @param type type of the searched device
     * @return first found device
     * @throws DeviceNotFoundException if no device with the specified type found
     */
    public static Device findDevice(DeviceType type) throws DeviceNotFoundException {
        return Simulation.getInstance().getDevices().stream()
                .filter(device -> device.getType() == type && device.notOccupied())
                .findFirst()
                .orElseThrow(DeviceNotFoundException::new);
    }

    /**
     * Finds device with one of the specified types.
     * @param type1 first possible type of the searched device
     * @param type2 second possible type of the searched device
     * @return first found device
     * @throws DeviceNotFoundException if no device with the specified types found
     */
    public static Device findDevice(DeviceType type1, DeviceType type2) throws DeviceNotFoundException {
        return Simulation.getInstance().getDevices().stream()
                .filter(device ->  device.notOccupied() && (device.getType() == type1 || device.getType() == type2))
                .findFirst()
                .orElseThrow(DeviceNotFoundException::new);
    }

    /**
     * Finds device with the specified type which is located in the specified room.
     * @param type type of the searched device
     * @param room room of the searched device
     * @return first found device
     * @throws DeviceNotFoundException if no device with the specified type in the specified room found
     */
    public static Device findDevice(DeviceType type, Room room) throws DeviceNotFoundException {
        return Simulation.getInstance().getDevices().stream()
                .filter(device -> device.getType() == type && device.getRoom() == room  && device.notOccupied())
                .findFirst()
                .orElseThrow(DeviceNotFoundException::new);
    }

    /**
     * Finds device with the specified type which is located in the room with the specified type.
     * @param type type of the searched device
     * @param roomType type of the room of the searched device
     * @return first found device
     * @throws DeviceNotFoundException if no device with the specified type in the room with the specified type found
     */
    public static Device findDevice(DeviceType type, RoomType roomType) throws DeviceNotFoundException {
        return Simulation.getInstance().getDevices().stream()
                .filter(device -> device.getType() == type && device.getRoom().getType() == roomType && device.notOccupied())
                .findFirst()
                .orElseThrow(DeviceNotFoundException::new);
    }

    /**
     * Finds room with the specified type.
     * @param type type of the searched room
     * @return first found room
     * @throws RoomNotFoundException if no room with the specified type found
     */
    public static Room findRoom(RoomType type) throws RoomNotFoundException {
        return Simulation.getInstance().getHome().getFloors().stream()
                .flatMap(floor -> floor.getRooms().stream())
                .filter(room -> room.getType() == type)
                .findFirst()
                .orElseThrow(RoomNotFoundException::new);
    }

    /**
     * Finds out what is the day period of the specified date.
     * @param date the inspected date
     * @return optional of day period of the specified date, or empty optional if cannot be decided
     */
    public static Optional<DayPeriod> getDayPeriod(LocalDateTime date) {
        LocalTime time = date.toLocalTime();
        for (DayPeriod period : DayPeriod.values()) {
            if (!time.isBefore(period.getStart()) && !time.isAfter(period.getEnd())) return Optional.of(period);
        }
        return Optional.empty();
    }
}
