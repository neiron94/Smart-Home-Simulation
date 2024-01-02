package creature;

import java.util.Deque;
import java.util.LinkedList;
import place.Location;
import smarthome.Simulation;
import place.Room;

public abstract class Creature {
    protected final String name;
    protected Location location;
    protected float hunger; // TODO Changes in routine function
    protected float fullness; // TODO Changes in routine function
    protected Activity activity; // TODO Changes in routine function
    protected boolean isBusy; // TODO Changes in routine function
    protected Deque<Deque<Action>> actions; // TODO Changes in routine function

    public Creature(String name, Location startLocation) {
        Simulation.getInstance().getCreatures().add(this);
        this.name = name;
        this.location = startLocation;

        hunger = 0;
        fullness = 0;

        actions = new LinkedList<>();
        activity = new Activity();
        isBusy = false;
    }

    public abstract void routine();

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public Activity getActivity() {
        return activity;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public Deque<Deque<Action>> getActions() {
        return actions;
    }

    public float getHunger() {
        return hunger;
    }

    public float getFullness() {
        return fullness;
    }

    public void die() {
        Simulation.getInstance().getCreatures().remove(this);
    }

    @Override
    public abstract String toString();
}
