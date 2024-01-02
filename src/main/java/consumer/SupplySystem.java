package consumer;

import consumer.device.Device;
import consumer.device.DeviceStatus;
import place.Room;
import smarthome.Simulation;

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
                .filter(consumer -> ((Device)consumer).getRoom() == room)
                .forEach(consumer -> ((Device)consumer).setStatus(switchOn ? ((Device)consumer).getType().getStartStatus() : DeviceStatus.OFF));
    }

    public void addConsumption(T consumer, double consumed) {
        consumedMap.put(consumer, consumed + consumedMap.get(consumer));
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
