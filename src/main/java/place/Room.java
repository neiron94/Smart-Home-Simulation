package place;

import consumer.device.sensored.*;
import consumer.device.sensored.sensor.ParameterSensor;
import event.Event;
import smarthome.Simulation;
import utils.HelpFunctions;
import java.util.ArrayList;
import java.util.List;

public class Room implements EventDestination {
    private final int id;
    private final RoomType type;
    private final Floor floor;
    private final ControlPanel controlPanel;
    private final List<Event> events;

    private boolean activeElectricity;
    private boolean activeWater;
    private boolean activeGas;

    private double temperature;
    private double humidity;
    private double brightness;

    public Room(int id, RoomType type, Floor floor) {
        this.id = id;
        this.type = type;
        this.floor = floor;
        controlPanel = new ControlPanel(this);
        events = new ArrayList<>();

        activeElectricity = true;
        activeWater = true;
        activeGas = true;
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

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getBrightness() {
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

    public RoomType getType() {
        return type;
    }

    public Floor getFloor() {
        return floor;
    }

    @Override
    public String toString() {
        return String.format("Room_%d (%s)", id, getRoomType().toString());
    }

    @Override
    public void deleteEvent(Event event) {
        events.remove(event);
    } // TODO For use by event solver

    public void routine() {
        Street street = Street.getInstance();
        temperature = street.getTemperature() * 0.6; // Room temperature is almost street one // TODO Move 0.6 to HOUSE constant
        humidity = street.getHumidity() * 0.9; // Room humidity is almost street one // TODO Move 0.9 to HOUSE constant
        brightness = street.getBrightness() * 0.8; // Room brightness is almost street one // TODO Move 0.8 to HOUSE constant

        Simulation.getInstance().getDevices().stream()
                .filter(device -> device.getRoom() == this && device instanceof ParameterDevice)
                .map(device -> (ParameterDevice<? extends ParameterSensor>) device)
                .forEach(device -> {
                    if (device instanceof Heater) temperature += 0.0 * device.getPower() / 100; // Consider working heater // TODO Make constant from Heater
                    else if (device instanceof AC) temperature -= 0.0 * device.getPower() / 100; // Consider working AC // TODO Make constant from AC
                    else if (device instanceof AirHumidifier) humidity = HelpFunctions.adjustPercent(humidity + 0.0 * device.getPower() / 100); // Consider working humidifier // TODO Make constant from AirHumidifier
                    else if (device instanceof AirDryer) humidity = HelpFunctions.adjustPercent(humidity - 0.0 * device.getPower() / 100); // Consider working dryer // TODO Make constant from AirDryer
                    else if (device instanceof Light) brightness = HelpFunctions.adjustPercent(brightness + 0.0 * device.getPower() / 100); // Consider working light // TODO Make constant from Light
                    else if (device instanceof Window) brightness = HelpFunctions.adjustPercent(brightness - 0.0 * device.getPower() / 100); // Consider working window // TODO Make constant from Window
                });
    }
}
