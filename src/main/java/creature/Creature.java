package creature;

import java.util.Deque;
import java.util.LinkedList;
import place.Location;
import smarthome.Simulation;
import place.Room;

public abstract class Creature {
    protected final String name;
    protected Location location;
    protected int abundance; // TODO Sets in routine function
    protected Activity activity; // TODO Sets in routine function
    protected boolean isBusy; // TODO Sets in routine function
    protected Deque<Deque<Action>> actions; // TODO Sets in routine function

    public Creature(String name, Location startLocation) {
        Simulation.getInstance().getResidents().add(this);
        this.name = name;
        this.location = startLocation;
        this.abundance = 0;
        this.activity = new Activity();
        this.isBusy = false;
        this.actions = new LinkedList<>();
    }

    public void routine() {
        // TODO Routine function
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public int getAbundance() {
        return abundance;
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

    public void delete() {
        Simulation.getInstance().getResidents().remove(this);
    }

    @Override
    public abstract String toString();
}
