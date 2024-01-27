/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

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
import static utils.Constants.Creature.*;
import static utils.HelpFunctions.makeRecord;

/**
 * Creatures perform actions, interact with devices and react on events
 * depending on their attributes and strategy. They have memory for possibility
 * of multiple interaction (for instance, person can start washing machine, go
 * cook food and then return to this washing machine when it's ready), also creatures
 * store information about their activity for future reports. Creature can survive
 * 1 week without food.
 */
public abstract class Creature {

    /**
     * Name of a creature.
     */
    protected final String name;

    /**
     * Room in which creature is located, or room in which
     * creature will return from street if not in home.
     */
    protected Room room;

    /**
     * Creature's activity for report.
     */
    protected final Activity activity;

    /**
     * Memory of a creature consists of queues of actions. Each queue of actions
     * represents sequence of actions which should be performed for some result.
     * For instance, to wash clothes, you should go to room with washer -> put clothes ->
     * start washer -> wait some time -> take clothes. Queues can have different priorities
     * ("solve fire event" is more important than "watch TV"), that's why {@link PriorityQueue} is used,
     * it can store items of different priorities in decreasing order. Java don't
     * have built-in Queue, which have priority, so Adapter pattern is used in {@link RankedQueue}.
     */
    protected final PriorityQueue<RankedQueue<? extends Action<? extends Creature, ?>>> memory;

    /**
     * Actual sequence of actions.
     */
    protected RankedQueue<? extends Action<? extends Creature, ?>> currentActivity;

    /**
     * Strategy for reacting on events.
     */
    protected Strategy strategy;

    /**
     * True if creature is at home. It cannot solve events if is not at home.
     */
    protected boolean atHome;

    /**
     * True is creature is alive.
     */
    protected boolean isAlive;

    /**
     * Hunger of a creature. If too hungry, chooses eat action.
     */
    protected double hunger;

    /**
     * Fullness of a creature. If too full, chooses toilet action.
     */
    protected double fullness;

    /**
     * Creates new creature.
     * @param name name of a creature
     * @param startRoom room where creature is located
     */
    public Creature(String name, Room startRoom) {
        Simulation.getInstance().getCreatures().add(this);
        this.name = name;
        this.room = startRoom;

        memory = new PriorityQueue<>();
        activity = new Activity();

        hunger = new Random().nextDouble(0, HUNGER_THRESHOLD / 2);
        fullness = new Random().nextDouble(0, FULLNESS_THRESHOLD / 2);

        currentActivity = null;
        isAlive = true;
        atHome = true;
    }

    /**
     * Is called every simulation tick from {@link Simulation#simulate()}.
     * Decides and adds next action to memory, perform current actions,
     * deletes completed actions, detects event and start reacting,
     * increases hunger and fullness.
     */
    public void routine() {
        memory.removeIf(queue -> {
            if (queue.isEmpty()) return true; // Remove empty actions queue
            queue.peek().decreaseDuration(1); // Decrease first action duration in queue
            return false;
        });

        if (atHome) {
            Stream.of(Simulation.getInstance().getHome().getEvents(), room.getFloor().getEvents(), room.getEvents())
                    .flatMap(List::stream)
                    .forEach(event -> { // Find event to solve
                        if (notPlanned(event.getPriority())) strategy.react(event); // Need to solve event
                        memory.stream().filter(queue -> queue.getPriority() == Priority.SLEEP && event.getPriority().getValue() > Priority.SLEEP.getValue())
                                .findAny().ifPresent(queue -> {  // Need to wake up
                                    memory.remove(queue);
                                    makeRecord(this, "Wake up to an event");
                                });
                    });
        }
        if (hunger > HUNGER_THRESHOLD && notPlanned(Priority.EAT)) decreaseHunger(); // Need to eat
        if (fullness > FULLNESS_THRESHOLD && notPlanned(Priority.EMPTY)) decreaseFullness(); // Need to empty myself
        if (currentActivity == null) chooseActivity(); // Nothing important is doing - take common activity

        for (RankedQueue<? extends Action<? extends Creature, ?>> queue : memory) {
            if (!queue.isEmpty() && queue.peek().getDuration().equals(Duration.ZERO) && (queue == currentActivity || !queue.peek().isBusy())) {
                if (queue.poll().perform()) {
                    if (!queue.isEmpty() && queue.peek().isBusy()) break;
                } else memory.remove(queue);
                setCurrent(queue);
                break;
            }
            if (currentActivity != null && !currentActivity.isEmpty() && !currentActivity.peek().isBusy()) setCurrent(queue);
        }

        hunger = HelpFunctions.adjustPercent(hunger + HUNGER_INCREASE + new Random().nextDouble(0, HUNGER_INCREASE));
        fullness = HelpFunctions.adjustPercent(fullness + FULLNESS_INCREASE + new Random().nextDouble(0, FULLNESS_INCREASE));
        if (fullness == 100) reactMaxFullness();
        if (hunger == 100) reactMaxHunger();
    }

    /**
     * Adds new sequence of actions to memory and sets it as actual.
     * @param sequence sequence of actions
     */
    public void addToMemory(RankedQueue<? extends Action<? extends Creature, ?>> sequence) {
        currentActivity = sequence;
        memory.add(sequence);
    }

    /**
     * Chooses sequence of actions to decrease hunger.
     */
    protected abstract void decreaseHunger();

    /**
     * Chooses sequence of actions to decrease fullness.
     */
    protected abstract void decreaseFullness();

    /**
     * Chooses new sequence of actions.
     */
    protected abstract void chooseActivity();

    /**
     * Reacts on max fullness.
     */
    protected abstract void reactMaxFullness();

    @Override
    public abstract String toString();

    private boolean notPlanned(Priority activity) {
        for (RankedQueue<? extends Action<? extends Creature, ?>> queue : memory) {
            if (queue.getPriority().getValue() == activity.getValue()) return false;
            if (queue.getPriority().getValue() < activity.getValue()) break;
        }
        return true;
    }

    private void setCurrent(RankedQueue<? extends Action<? extends Creature, ?>> sequence) {
        for (RankedQueue<? extends Action<? extends Creature, ?>> queue : memory) {
            if (queue != sequence && !queue.isEmpty() && !queue.peek().isBusy()) {
                currentActivity = queue;
                return;
            }
        }
        currentActivity = null;
    }

    private void reactMaxHunger() {
        makeRecord(this, "Die");
        isAlive = false;
        if (fullness > 0) reactMaxFullness();
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

    public boolean notBusy() {
        return currentActivity == null;
    }

    public void setAtHome(boolean atHome) {
        this.atHome = atHome;
    }

    public boolean isAtHome() {
        return atHome;
    }
}
