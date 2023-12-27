package creature;

import java.util.Deque;
import java.util.LinkedList;

import smarthome.Simulation;
import place.Room;
import utils.Percent;

public abstract class Creature {
    protected final String name;
    protected Room room; // TODO What if null - Creature can be outside
    protected Percent abundance; // TODO Sets in routine function
    protected Activity activity; // TODO Sets in routine function
    protected boolean isBusy; // TODO Sets in routine function
    protected Deque<Deque<Action>> actions; // TODO Sets in routine function

    public Creature(String name) {
        this.name = name;
        this.room = null; // TODO Think of storing Optional or Location
        this.abundance = new Percent(100);
        this.activity = new Activity();
        this.isBusy = false;
        this.actions = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Percent getAbundance() {
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

    public void routine() {
        // TODO Routine function
    }
}
