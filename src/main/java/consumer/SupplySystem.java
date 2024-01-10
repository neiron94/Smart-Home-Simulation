package consumer;

import consumer.device.Device;
import place.Room;
import smarthome.Simulation;
import utils.exceptions.DeviceIsBrokenException;
import utils.exceptions.ResourceNotAvailableException;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents system, which can store information about consumed resources (electricity/gas/water)
 * by all device in the house in total and which can switch on/off resource in whole house/concrete room.
 * @param <T> Concrete Consumer type which describes what resource this supply system is responsible for.
 */
public class SupplySystem<T extends Consumer> {
    private final Map<T, Double> consumedMap;
    private boolean resourceAvailable;

    public SupplySystem() {
        consumedMap = new HashMap<>();
        resourceAvailable = true;
    }

    /**
     * Sets each value in consumed map to zero.
     */
    public void restoreConsumptions() {
        for (Map.Entry<T, Double> consumption : consumedMap.entrySet())
            consumption.setValue(0.0);
    }

    /**
     * Turns all devices in house on or off depending on argument.
     * @param switchOn true -> turn on, false -> turn off.
     */
    public void switchAll(boolean switchOn) {
        Simulation.getInstance().getHome().getFloors().stream()
                .flatMap(floor -> floor.getRooms().stream())
                .forEach(room -> switchRoom(room, switchOn));
    }

    /**
     * Turns all devices in concrete room on or off depending on argument.
     * @param room Concrete room.
     * @param switchOn true -> turn on, false -> turn off.
     */
    public void switchRoom(Room room, boolean switchOn) {
        consumedMap.keySet().stream()
                .filter(consumer -> ((Device) consumer).getRoom() == room)
                .forEach(consumer -> {
                    if (switchOn) {
                        try {
                            ((Device) consumer).turnOn();
                        } catch (DeviceIsBrokenException | ResourceNotAvailableException ignored) {}
                    } else ((Device) consumer).turnOff();
                });
    }

    /**
     * Adds consumption to consumed map.
     * @param consumer Consumer which consumes.
     * @param consumed Consumed amount.
     */
    public void addConsumption(T consumer, double consumed) {
        if (consumedMap.containsKey(consumer)) consumedMap.put(consumer, consumedMap.get(consumer) + consumed);
        else consumedMap.put(consumer, consumed);
    }

    /**
     * Shutdowns the system. Devices, which use this resource,
     * are turned off and can't work until recover. Is called randomly from Simulation.
     */
    public void shutdown() {
        this.resourceAvailable = false;
        switchAll(false);
    }

    /**
     * Recovers the system. Devices, which use this resource,
     * are automatically restarted. Is called from Simulation after random time after shutdown.
     */
    public void recover() {
        this.resourceAvailable = true;
        switchAll(true);
    }

    /**
     * Adds new consumer to consumed map, should be used only by AddVisitor.
     * @param consumer New consumer.
     */
    public void addConsumer(T consumer) {
        consumedMap.put(consumer, 0.0);
    }

    /**
     * Deletes consumer from consumed map, should be used only by DeleteVisitor.
     * @param consumer Consumer to delete.
     */
    public void deleteConsumer(T consumer) {
        consumedMap.remove(consumer);
    }

    public Map<T, Double> getConsumedMap() {
        return consumedMap;
    }

    public boolean isResourceAvailable() {
        return resourceAvailable;
    }
}
