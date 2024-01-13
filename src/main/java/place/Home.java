package place;

import consumer.supplySystem.ElectricitySupplySystem;
import consumer.supplySystem.GasSupplySystem;
import consumer.supplySystem.WaterSupplySystem;
import event.Event;
import java.util.ArrayList;
import java.util.List;

/**
 * Root place in home topology which contains floors and supply systems.
 */
public class Home implements EventDestination {
    private final List<Floor> floors;
    private final List<Event> events;

    private final ElectricitySupplySystem electricitySupplySystem;
    private final WaterSupplySystem waterSupplySystem;
    private final GasSupplySystem gasSupplySystem;

    /**
     * Creates home, automatically creates all supply systems.
     */
    public Home() {
        floors = new ArrayList<>();
        events = new ArrayList<>();
        electricitySupplySystem = new ElectricitySupplySystem();
        waterSupplySystem = new WaterSupplySystem();
        gasSupplySystem = new GasSupplySystem();
    }

    /**
     * Deletes event from this home.
     * @param event event to delete
     * @return true if home contained the specified event
     */
    @Override
    public boolean deleteEvent(Event event) {
        return events.remove(event);
    }

    /**
     * Adds event to this home.
     * @param event event to add
     */
    @Override
    public void addEvent(Event event) {
        events.add(event);
    }

    /**
     * Adds floor to this home.
     * @param floor floor to add
     */
    public void addFloor(Floor floor) {
        floors.add(floor);
    }

    @Override
    public String toString() {
        return "Home";
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public List<Event> getEvents() {
        return events;
    }

    public ElectricitySupplySystem getElectricitySupplySystem() {
        return electricitySupplySystem;
    }

    public WaterSupplySystem getWaterSupplySystem() {
        return waterSupplySystem;
    }

    public GasSupplySystem getGasSupplySystem() {
        return gasSupplySystem;
    }
}
