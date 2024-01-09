package creature;

import java.time.Duration;
import java.util.*;
import java.util.stream.Stream;
import creature.strategy.Strategy;
import smarthome.Simulation;
import place.Room;
import utils.Priority;
import utils.RankedQueue;

public abstract class Creature {
    protected final String name;
    protected Room room;

    protected final Activity activity;
    protected final TreeSet<RankedQueue<? extends Action<? extends Creature, ?>>> memory; // TODO Second class ???
    protected Strategy strategy;

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
                    .forEach(event -> { // Find event to solve
                        if (memory.isEmpty() || memory.first().getPriority() < event.getPriority().getValue()) {
                            strategy.react(event);
                            isBusy = true;
                        }
                    });
        }
        if (hunger > 0 && notPlanned(Priority.EAT)) decreaseHunger(); // Need to eat // TODO Make constant
        if (fullness > 0 && notPlanned(Priority.EMPTY)) decreaseFullness(); // Need to empty myself // TODO Make constant
        if (!isBusy) chooseActivity(); // Nothing important is doing - take new activity

        boolean canDoAction = true;
        for (RankedQueue<? extends Action<? extends Creature, ?>> queue : memory) { // TODO Second class ???
            queue.peek().decreaseDuration(1); // Decrease duration of action start
            if (queue.peek().getDuration().equals(Duration.ZERO) && !isBusy && canDoAction) {
                Action<? extends Creature, ?> action = queue.poll(); // TODO Second class ???
                if (action.perform()) {
                    isBusy = queue.peek().isBusy();
                    canDoAction = false; // Can do only one action per simulation tick
                }
                else {
                    memory.remove(queue);
                    isBusy = false;
                }
            }
        }

        hunger += 0; // TODO Make constant
        fullness += 0; // TODO Make constant

        if (fullness == 100) reactMaxFullness();
        if (hunger == 100) reactMaxHunger();
    }

    private boolean notPlanned(Priority activity) {
        for (RankedQueue<? extends Action<? extends Creature, ?>> queue : memory) { // TODO Second class ???
            if (queue.getPriority() == activity.getValue()) return false;
            if (queue.getPriority() < activity.getValue()) break;
        }
        return true;
    }

    protected abstract void decreaseHunger(); // TODO Second class ???

    protected abstract void decreaseFullness(); // TODO Second class ???

    protected abstract void chooseActivity(); // TODO Second class ???

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

    public void setRoom(Room room) {
        this.room = room;
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

    public Strategy getStrategy() {
        return strategy;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public boolean isAtHome() {
        return atHome;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void addToMemory(RankedQueue<? extends Action<? extends Creature, ?>> sequence) {
        memory.add(sequence);
    }

    @Override
    public abstract String toString();
}
