/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package place;

import consumer.device.sensored.*;
import consumer.device.sensored.sensor.ParameterSensor;
import consumer.supplySystem.SupplySystem;
import event.Event;
import smarthome.Simulation;
import utils.Constants.ParameterDevices;
import utils.Constants.House;
import utils.HelpFunctions;
import java.util.ArrayList;
import java.util.List;

/**
 * Leaf place in home topology. Contains devices and control panel.
 * Has attributes: temperature, humidity and brightness.
 * Resources can be blocked in the room by {@link SupplySystem}.
 */
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
    private double humidity; // percent
    private double brightness; // percent

    /**
     * Creates new room.
     * @param id id of the room, should be unique
     * @param type type of the room
     * @param floor floor, where this room is located
     */
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

    /**
     * Counts and sets room attributes depending on street attributes
     * and power of every {@link consumer.device.sensored.ParameterDevice} in this room.
     * Is called every tick in {@link Simulation#simulate()}.
     */
    public void routine() {
        Street street = Street.getInstance();
        temperature = street.getTemperature() * House.HEAT_PENETRABILITY; // Room temperature is almost street one
        humidity = street.getHumidity() * House.HUMIDITY_PENETRABILITY; // Room humidity is almost street one
        brightness = street.getBrightness() * House.LIGHT_PENETRABILITY; // Room brightness is almost street one

        Simulation.getInstance().getDevices().stream()
                .filter(device -> device.getRoom() == this && device instanceof ParameterDevice)
                .map(device -> (ParameterDevice<? extends ParameterSensor>) device)
                .forEach(device -> {
                    if (device instanceof Heater) temperature += ParameterDevices.HEATER_ON_MAX_POWER * device.getPower() / 100; // Consider working heater
                    else if (device instanceof AC) temperature -= ParameterDevices.AC_ON_MAX_POWER * device.getPower() / 100; // Consider working AC
                    else if (device instanceof AirHumidifier) setHumidity(humidity + ParameterDevices.HUMIDIFIER_ON_MAX_POWER * device.getPower() / 100); // Consider working humidifier
                    else if (device instanceof AirDryer) setHumidity(humidity - ParameterDevices.DRYER_ON_MAX_POWER * device.getPower() / 100); // Consider working dryer
                    else if (device instanceof Light) setBrightness(brightness + ParameterDevices.LIGHT_ON_MAX_POWER * device.getPower() / 100); // Consider working light
                    else if (device instanceof Window) setBrightness(brightness - ParameterDevices.WINDOW_ON_MAX_POWER * device.getPower() / 100); // Consider working window
                });
    }

    /**
     * Deletes event from this room.
     * @param event event to delete
     * @return true if room contained the specified event
     */
    @Override
    public boolean deleteEvent(Event event) {
        return events.remove(event);
    }

    /**
     * Adds event to this room.
     * @param event event to add
     */
    @Override
    public void addEvent(Event event) {
        events.add(event);
    }

    @Override
    public String toString() {
        return String.format("Room_%d (%s)", id, getType().toString());
    }

    public List<Event> getEvents() {
        return events;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    private void setHumidity(double humidity) {
        this.humidity = HelpFunctions.adjustPercent(humidity);
    }

    public double getBrightness() {
        return brightness;
    }

    private void setBrightness(double brightness) {
        this.brightness = HelpFunctions.adjustPercent(brightness);
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

    public int getId() {
        return id;
    }

    public RoomType getType() {
        return type;
    }

    public Floor getFloor() {
        return floor;
    }
}
