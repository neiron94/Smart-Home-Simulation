package place;

import utils.Percent;
import java.util.ArrayList;

public class Room implements EventDestination {
    private static int id = 1;

    private final int roomId;
    private final RoomType type;
    private final ControlPanel controlPanel;
    private List<Event> events; // TODO Control after creating Event class

    private boolean activeElectricity;
    private boolean activeWater;
    private boolean activeGas;

    private double temperature;
    private Percent humidity; // TODO Control after creating Percent class
    private Percent brightness; // TODO Control after creating Percent class

    public Room(RoomType type) {
        roomId = id++;
        this.type = type;
        controlPanel = new ControlPanel(this); // TODO Maybe not to give this to ControlPanel, but accept this by ControlPanel
        events = new ArrayList<>();

        activeElectricity = true;
        activeWater = true;
        activeGas = true;

        temperature = ; // TODO Add default temperature value
        humidity = ; // TODO Add default humidity value
        brightness = ; // TODO Add default brightness value
    }

    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) { // TODO Control after creating Event class
        events.add(event);
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public Percent getHumidity() {
        return humidity;
    }

    public void setHumidity(Percent humidity) {
        this.humidity = humidity;
    }

    public Percent getBrightness() {
        return brightness;
    }

    public void setBrightness(Percent brightness) {
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

    @Override
    public String toString() {
        return "Room_" + roomId + " (" + getRoomType().toString() + ")";
    }

    @Override
    public void deleteEvent(Event event) { // TODO Control after creating Event class
        events.remove(event);
    } // TODO For use by event solver
}
