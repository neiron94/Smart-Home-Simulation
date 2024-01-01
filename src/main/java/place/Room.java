package place;

import event.Event;

import java.util.ArrayList;
import java.util.List;

public class Room implements EventDestination {
    private final int id;
    private final RoomType type;
    private final ControlPanel controlPanel;
    private List<Event> events;

    private boolean activeElectricity;
    private boolean activeWater;
    private boolean activeGas;

    private double temperature;
    private int humidity;
    private int brightness;

    public Room(int id, RoomType type) {
        this.id = id;
        this.type = type;
        controlPanel = new ControlPanel(this); // TODO Maybe not to give this to ControlPanel, but accept this by ControlPanel
        events = new ArrayList<>();

        activeElectricity = true;
        activeWater = true;
        activeGas = true;

        temperature = 0; // TODO Add default temperature value
        humidity = 0; // TODO Add default humidity value
        brightness = 0; // TODO Add default brightness value
    }

    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public boolean isActiveElectricity() {
        return activeElectricity;
    }

    public void setActiveElectricity(boolean activeElectricity) {
        this.activeElectricity = activeElectricity;
    }

    public boolean isActiveWater() {
        return activeWater;
    }

    public void setActiveWater(boolean activeWater) {
        this.activeWater = activeWater;
    }

    public boolean isActiveGas() {
        return activeGas;
    }

    public void setActiveGas(boolean activeGas) {
        this.activeGas = activeGas;
    }

    public RoomType getRoomType() {
        return type;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("%s_%d (%s)", "Room_", id, getRoomType().toString());
    }

    @Override
    public void deleteEvent(Event event) {
        events.remove(event);
    } // TODO For use by event solver
}
