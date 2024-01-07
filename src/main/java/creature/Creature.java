package creature;

import java.util.Deque;
import java.util.LinkedList;
import smarthome.Simulation;
import place.Room;

public abstract class Creature {
    protected final String name;
    protected Room room;

    protected float hunger; // TODO Changes in routine function
    protected float fullness; // TODO Changes in routine function

    protected final Activity activity; // TODO Changes in routine function
    protected final Deque<Deque<Action>> memory; // TODO Changes in routine function
    protected boolean isBusy; // TODO Changes in routine function
    protected boolean isAlive;

    public Creature(String name, Room startRoom) {
        Simulation.getInstance().getCreatures().add(this);
        this.name = name;
        this.room = startRoom;

        hunger = 0;
        fullness = 0;

        memory = new LinkedList<>();
        activity = new Activity();
        isBusy = false;
        isAlive = true;
    }

    public void routine() {
        if (hunger == 100) reactMaxHunger();
        if (fullness == 100) reactMaxFullness();
    }

    protected abstract void decreaseHunger();

    protected abstract void decreaseFullness();

    protected abstract void chooseActivity();

    protected abstract void reactMaxFullness();

    private void reactMaxHunger() {
        activity.addActivity("Die");
        isAlive = false;
    }

    public String getName() {
        return name;
    }

    public Room getRoom() {
        return room;
    }

    public Activity getActivity() {
        return activity;
    }

    public float getHunger() {
        return hunger;
    }

    public float getFullness() {
        return fullness;
    }

    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public abstract String toString();
}
