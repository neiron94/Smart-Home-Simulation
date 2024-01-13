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
     * Strategy for reacting on events.
     */
    protected Strategy strategy;

    /**
     * True if creature is at home. It cannot solve events if is not at home.
     */
    protected boolean atHome;

    /**
     * True if creature is doing some busy action.
     */
    protected boolean isBusy;

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

        isBusy = false;
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
        if (atHome) {
            Stream.concat(Simulation.getInstance().getHome().getEvents().stream(),
                            Stream.concat(room.getFloor().getEvents().stream(), room.getEvents().stream()))
                    .forEach(event -> { // Find event to solve
                        strategy.react(event); // Need to solve event
                        if (event.getPriority().getValue() > Priority.SLEEP.getValue()) { // Need to wake up
                            memory.removeIf(queue -> {
                                if (queue.getPriority() == Priority.SLEEP) {
                                    makeRecord(this, "Wake up to an event");
                                    return true;
                                }
                                return false;
                            });
                        }
                    });
        }
        if (hunger > HUNGER_THRESHOLD && notPlanned(Priority.EAT)) decreaseHunger(); // Need to eat
        if (fullness > FULLNESS_THRESHOLD && notPlanned(Priority.EMPTY)) decreaseFullness(); // Need to empty myself
        if (!isBusy) chooseActivity(); // Nothing important is doing - take new activity

        memory.removeIf(RankedQueue::isEmpty); // Remove empty actions queue
        memory.forEach(queue -> queue.peek().decreaseDuration(1)); // Decrease first action duration in queue

        boolean[] canDoAction = {true}; // Array for changing value in stream
        memory.forEach(queue -> {
                    if (queue.peek().getDuration().equals(Duration.ZERO) && canDoAction[0]) { // Action can be performed // TODO Bug - Doing actions when busy by other actions
                        if (queue.poll().perform()) { // Successful perform
                            canDoAction[0] = false; // Can do only one action per simulation tick
                            isBusy = !queue.isEmpty() && queue.peek().isBusy();
                        } else { // Unsuccessful perform
                            queue.clear();
                            isBusy = false;
                        }
                    }
                });

        hunger = HelpFunctions.adjustPercent(hunger + HUNGER_INCREASE + new Random().nextDouble(0, HUNGER_INCREASE));
        fullness = HelpFunctions.adjustPercent(fullness + FULLNESS_INCREASE + new Random().nextDouble(0, FULLNESS_INCREASE));
        if (fullness == 100) reactMaxFullness();
        if (hunger == 100) reactMaxHunger();
    }

    /**
     * Adds new sequence of actions to memory.
     * @param sequence sequence of actions
     */
    public void addToMemory(RankedQueue<? extends Action<? extends Creature, ?>> sequence) {
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

    public boolean isBusy() {
        return isBusy;
    }

    public void setAtHome(boolean atHome) {
        this.atHome = atHome;
    }

    public boolean isAtHome() {
        return atHome;
    }
}
