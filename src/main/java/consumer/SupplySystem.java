package consumer;

import consumer.device.Device;
import place.Room;
import smarthome.Simulation;
import utils.exceptions.DeviceIsBrokenException;
import utils.exceptions.ResourceNotAvailableException;

import java.util.HashMap;
import java.util.Map;

public class SupplySystem<T extends Consumer> {
    private final Map<T, Double> consumedMap;
    private boolean resourceAvailable;

    public SupplySystem() {
        consumedMap = new HashMap<>();
        resourceAvailable = true;
    }

    public void restoreConsumptions() {     // Sets each value in consumed map to zero
        for (Map.Entry<T, Double> consumption : consumedMap.entrySet()) {
            consumption.setValue(0.0);
        }
    }

    public void switchAll(boolean switchOn) {
        Simulation.getInstance().getHome().getFloors().stream()
                .flatMap(floor -> floor.getRooms().stream())
                .forEach(room -> switchRoom(room, switchOn));
    }

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

    public void addConsumption(T consumer, double consumed) {
        if (consumedMap.containsKey(consumer)) consumedMap.put(consumer, consumedMap.get(consumer) + consumed);
        else consumedMap.put(consumer, consumed);
    }

    public void addConsumer(T consumer) {
        consumedMap.put(consumer, 0.0);
    }

    public void deleteConsumer(T consumer) {
        consumedMap.remove(consumer);
    }

    public Map<T, Double> getConsumedMap() {
        return consumedMap;
    }

    public boolean isResourceAvailable() {
        return resourceAvailable;
    }

    public void setResourceAvailable(boolean resourceAvailable) {
        this.resourceAvailable = resourceAvailable;
    }
}
