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

import java.util.List;
import java.util.Random;

public class HelpFunctions {
    public static int adjustPercent(int value) {
        return value < 0 ? 0 : Math.min(value, 100);
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
        return status.getMultiplier() * power * Constants.Time.TICK_DURATION;
    }

    public static double countWaterConsumption(DeviceStatus status, double power) {
        return status == DeviceStatus.ON ? power * Constants.Time.TICK_DURATION : 0;
    }

    public static double countGasConsumption(DeviceStatus status, double power) {
        return status == DeviceStatus.ON ? power * Constants.Time.TICK_DURATION : 0;
    }

    public static void ignore(){}

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

    public static Room getRandomRoom() {
        return getRandomObject(Simulation.getInstance().getHome().getFloors().stream()
                .flatMap(floor -> floor.getRooms().stream())
                .toList());
    }

    public static Room findRoom(RoomType type) throws RoomNotFoundException {
        return Simulation.getInstance().getHome().getFloors().stream()
                .flatMap(floor -> floor.getRooms().stream())
                .filter(room -> room.getType() == type)
                .findFirst()
                .orElseThrow(RoomNotFoundException::new);
    }

    public static Device getRandomDevice() {
        return getRandomObject(Simulation.getInstance().getDevices().stream().toList());
    }
}
