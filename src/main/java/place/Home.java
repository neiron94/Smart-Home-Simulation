package place;

import consumer.ElectricityConsumer;
import consumer.GasConsumer;
import consumer.SupplySystem;
import consumer.WaterConsumer;
import event.Event;

import java.util.ArrayList;
import java.util.List;

public class Home implements EventDestination {
    private final List<Floor> floors;
    private List<Event> events;

    private final SupplySystem<ElectricityConsumer> electricitySupplySystem;
    private final SupplySystem<WaterConsumer> waterSupplySystem;
    private final SupplySystem<GasConsumer> gasSupplySystem;

    public Home() {
        floors = new ArrayList<>();
        events = new ArrayList<>();
        electricitySupplySystem = new SupplySystem<>();
        waterSupplySystem = new SupplySystem<>();
        gasSupplySystem = new SupplySystem<>();
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void addFloor(Floor floor) {
        floors.add(floor);
    }

    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public SupplySystem<ElectricityConsumer> getElectricitySupplySystem() {
        return electricitySupplySystem;
    }

    public SupplySystem<WaterConsumer> getWaterSupplySystem() {
        return waterSupplySystem;
    }

    public SupplySystem<GasConsumer> getGasSupplySystem() {
        return gasSupplySystem;
    }

    @Override
    public String toString() {
        return "Home";
    }

    @Override
    public void deleteEvent(Event event) {
        events.remove(event);
    } // TODO For use by event solver
}
