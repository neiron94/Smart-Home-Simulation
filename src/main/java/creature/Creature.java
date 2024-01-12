package creature;

import java.time.Duration;
import java.util.*;
import java.util.stream.Stream;
import creature.strategy.Strategy;
import smarthome.Simulation;
import place.Room;
import utils.HelpFunctions;
import utils.Priority;
import utils.RankedQueue;
import static utils.HelpFunctions.makeRecord;

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
                            new Action<>(1, true, this, null, action -> {
                                makeRecord(action.getExecutor(), "Wake up to an event");
                                return true;
                            }).perform();
                        }
                    });
        }
        if (hunger > 0 && notPlanned(Priority.EAT)) decreaseHunger(); // Need to eat // TODO Constant
        if (fullness > 0 && notPlanned(Priority.EMPTY)) decreaseFullness(); // Need to empty myself // TODO Constant
        //if (!isBusy) chooseActivity(); // Nothing important is doing - take new activity

        Iterator<RankedQueue<? extends Action<? extends Creature, ?>>> memoryIterator = memory.iterator();
        while (memoryIterator.hasNext()) {
            RankedQueue<? extends Action<? extends Creature, ?>> queue = memoryIterator.next();
            if (queue.isEmpty()) memoryIterator.remove(); // Remove empty actions queue
            else queue.peek().decreaseDuration(1); // Decrease first action duration in queue
        }

        boolean[] canDoAction = {true}; // Array for changing value in stream
        memory.forEach(queue -> {
                    if (queue.peek().getDuration().equals(Duration.ZERO) && canDoAction[0]) { // Action can be performed
                        if (queue.poll().perform()) { // Successful perform
                            canDoAction[0] = false; // Can do only one action per simulation tick
                            isBusy = !queue.isEmpty() && queue.peek().isBusy();
                        } else { // Unsuccessful perform
                            queue.clear();
                            isBusy = false;
                        }
                    }
                });

        hunger += 0; // TODO Constant
        fullness += 0; // TODO Constant
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

    public boolean isBusy() {
        return isBusy;
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
