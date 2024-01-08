package creature;

import java.time.Duration;
import java.util.*;
import java.util.stream.Stream;

import event.Event;
import smarthome.Simulation;
import place.Room;
import utils.Priority;
import utils.RankedQueue;

public abstract class Creature {
    protected final String name;
    protected Room room;

    protected final Activity activity;
    protected final TreeSet<RankedQueue<Action>> memory;

    protected boolean atHome;
    protected boolean isBusy;
    protected boolean isAlive;

    protected float hunger;
    protected float fullness;

    public Creature(String name, Room startRoom) {
        Simulation.getInstance().getCreatures().add(this);
        this.name = name;
        this.room = startRoom;

        memory = new TreeSet<>();
        activity = new Activity();

        hunger = 0;
        fullness = 0;

        isBusy = false;
        isAlive = true;
        atHome = true;
    }

    public void routine() {
        if (atHome) {
            Stream.concat(Simulation.getInstance().getHome().getEvents().stream(),
                            Stream.concat(room.getFloor().getEvents().stream(), room.getEvents().stream()))
                    .forEach(event -> { // Find event to solve // TODO Make event solving strategy + add strategy attribute
                        if (memory.isEmpty() || memory.first().getPriority() < event.getPriority().getValue()) {
                            strategy.solve(event);
                            isBusy = true;
                        }
                    });

            // TODO Event can be already solved in future -> catch it in solving strategy and return false here -> forget queue
        }
        if (hunger > 0 && notPlanned(Priority.EAT)) decreaseHunger(); // Need to eat // TODO Make constant
        if (fullness > 0 && notPlanned(Priority.EMPTY)) decreaseFullness(); // Need to empty myself // TODO Make constant
        if (!isBusy) chooseActivity(); // Nothing important is doing - take new activity

        boolean canDoAction = true;
        for (RankedQueue<Action> queue : memory) {
            queue.peek().decreaseDuration(1); // Decrease duration of action start
            if (queue.peek().getDuration().equals(Duration.ZERO) && !isBusy && canDoAction) {
                Action action = queue.poll();
                if (action.perform()) {
                    activity.addActivity(action.getDescription()); // Write performed activity
                    activity.increaseUsage(action.getDevice()); // Write device usage
                    isBusy = queue.peek().isBusy();
                } else {
                    memory.remove(queue);
                    isBusy = false;
                }

                canDoAction = false; // Can do only one action per simulation tick
            }
        }

        hunger += 0; // TODO Make constant
        fullness += 0; // TODO Make constant

        if (fullness == 100) reactMaxFullness();
        if (hunger == 100) reactMaxHunger();
    }

    private boolean notPlanned(Priority activity) {
        for (RankedQueue<Action> queue : memory) {
            if (queue.getPriority() == activity.getValue()) return false;
            if (queue.getPriority() < activity.getValue()) break;
        }
        return true;
    }

    protected abstract void decreaseHunger();

    protected abstract void decreaseFullness();

    protected abstract void chooseActivity();

    protected abstract void reactMaxFullness();

    private void reactMaxHunger() {
        activity.addActivity("Die");
        if (fullness > 0) reactMaxHunger();
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
