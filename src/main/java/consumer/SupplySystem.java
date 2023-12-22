package consumer;

import place.Room;

import java.util.HashMap;
import java.util.Map;

public class SupplySystem<T extends Consumer> {
    private Map<T, Integer> consumedMap;
    private boolean resourceAvailable;

    public SupplySystem() {
        // TODO - should initialize map (add devices)
        consumedMap = new HashMap<>();
        resourceAvailable = true;
    }

    public void restoreConsumptions() {     // Sets each value in consumed map to zero
        for (Map.Entry<T, Integer> consumption : consumedMap.entrySet()) {
            consumption.setValue(0);
        }
    }

    public void switchAll(boolean switchOn) {
        // TODO - implement. For each room in home call switchRoom(room, switchOn)
    }

    public void switchRoom(Room room, boolean switchOn) {
        // TODO - implement. For each device from consumed map in this room set status to 1) OFF if switchOn is false 2) START_STATUS if true
    }

    public void addConsumption(T consumer, int consumed) {
        consumedMap.put(consumer, consumed + consumedMap.get(consumer));
    }

    public void addConsumer(T consumer) {
        consumedMap.put(consumer, 0);
    }

    public void deleteConsumer(T consumer) {
        consumedMap.remove(consumer);
    }

    public Map<T, Integer> getConsumedMap() {
        return consumedMap;
    }

    public boolean isResourceAvailable() {
        return resourceAvailable;
    }

    public void setResourceAvailable(boolean resourceAvailable) {
        this.resourceAvailable = resourceAvailable;
    }
}
