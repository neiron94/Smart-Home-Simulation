package creature;

import java.time.Duration;
import java.util.*;
import java.util.stream.Stream;

import creature.person.PersonAPI;
import creature.strategy.Strategy;
import smarthome.Simulation;
import place.Room;
import utils.HelpFunctions;
import utils.Priority;
import utils.RankedQueue;

public abstract class Creature {
    protected final String name;
    protected Room room;

    protected final Activity activity;
    protected final TreeSet<RankedQueue<? extends Action<? extends Creature, ?>>> memory;
    protected Strategy strategy;

    protected boolean atHome;
    protected boolean isBusy;
    protected boolean isAlive;

    protected double hunger;
    protected double fullness;

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
                        strategy.react(event); // Need to solve event
                        if (event.getPriority().getValue() > Priority.SLEEP.getValue()) { // Need to wake up
                            memory.removeIf(queue -> queue.getPriority() == Priority.SLEEP);
                            new Action<>(0, true, this, null, PersonAPI.wakeUp).perform();
                        }
                    });
        }
        if (hunger > 0 && notPlanned(Priority.EAT)) decreaseHunger(); // Need to eat // TODO Make constant
        if (fullness > 0 && notPlanned(Priority.EMPTY)) decreaseFullness(); // Need to empty myself // TODO Make constant
        if (!isBusy) chooseActivity(); // Nothing important is doing - take new activity

        boolean canDoAction = true;
        for (RankedQueue<? extends Action<? extends Creature, ?>> queue : memory) {
            queue.peek().decreaseDuration(1); // Decrease duration of action start
            if (queue.peek().getDuration().equals(Duration.ZERO) && !isBusy && canDoAction) {
                if (queue.poll().perform()) {
                    canDoAction = false; // Can do only one action per simulation tick
                    isBusy = queue.peek().isBusy();
                } else {
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
        for (RankedQueue<? extends Action<? extends Creature, ?>> queue : memory) {
            if (queue.getPriority().getValue() == activity.getValue()) return false;
            if (queue.getPriority().getValue() < activity.getValue()) break;
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

    public void setRoom(Room room) {
        this.room = room;
    }

    public Activity getActivity() {
        return activity;
    }

    public double getHunger() {
        return hunger;
    }

    public double getFullness() {
        return fullness;
    }

    public void setHunger(double hunger) {
        this.hunger = HelpFunctions.adjustPercent(hunger);
    }

    public void setFullness(double fullness) {
        this.fullness = HelpFunctions.adjustPercent(fullness);
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAtHome(boolean atHome) {
        this.atHome = atHome;
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
