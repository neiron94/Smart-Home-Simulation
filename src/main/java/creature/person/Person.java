package creature.person;

import creature.Action;
import creature.Creature;
import creature.strategy.ChildStrategy;
import creature.strategy.ManStrategy;
import creature.strategy.WomanStrategy;
import event.Event;
import place.Room;
import smarthome.Simulation;
import utils.DayPeriod;
import utils.HelpFunctions;
import utils.RankedQueue;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import static utils.HelpFunctions.makeRecord;

/**
 * Person interacts with devices and can perform many actions
 * with them. Can solve events.
 */
public class Person extends Creature {
    private final Gender gender;
    private final FamilyStatus status;
    private final Map<Event, LocalDateTime> solvedEvents;

    /**
     * Creates new person. Automatically sets strategy
     * for reacting on events depending on family status and gender.
     * @param name name of a person
     * @param gender gender of a person
     * @param status family status of a person
     * @param startRoom room, where person is located
     */
    public Person(String name, Gender gender, FamilyStatus status, Room startRoom) {
        super(name, startRoom);
        this.gender = gender;
        this.status = status;
        solvedEvents = new HashMap<>();

        if (status == FamilyStatus.KID) strategy = new ChildStrategy(this);
        else strategy = gender == Gender.MALE ? new ManStrategy(this) : new WomanStrategy(this);
    }

    /**
     * Adds new solved event for future report.
     * @param solvedEvent solved event
     */
    public void addSolvedEvent(Event solvedEvent) {
        solvedEvents.put(solvedEvent, Simulation.getInstance().getCurrentTime());
    }

    /**
     * Chooses action for decreasing hunger depending on current time.
     */
    @Override
    protected void decreaseHunger() {
        HelpFunctions.getRandomObject(List.of(PersonAPI.takeBreakfast, PersonAPI.takeLunch, PersonAPI.takeDinner))
                .ifPresent(function -> addToMemory(function.apply(this)));
    }

    /**
     * Chooses action for decreasing fullness depending on fullness.
     */
    @Override
    protected void decreaseFullness() {
        List<Function<Person, RankedQueue<Action<Person, ?>>>> functions = List.of(PersonAPI.pee, PersonAPI.poo);
        if (new Random().nextInt(0, 2) == 0) addToMemory(PersonAPI.poo.apply(this));
        else HelpFunctions.getRandomObject(functions).ifPresent(function -> addToMemory(function.apply(this)));
    }

    /**
     * Chooses new action depending on current time.
     */
    @Override
    protected void chooseActivity() {
        DayPeriod period = HelpFunctions.getDayPeriod(Simulation.getInstance().getCurrentTime()).orElse(DayPeriod.NIGHT);
        if (period == DayPeriod.NIGHT) addToMemory(PersonAPI.sleep.apply(this));
        else {
            List<Function<Person, RankedQueue<Action<Person, ?>>>> functions = Math.random() < 0.5 ? PersonAPI.streetFunctions : PersonAPI.otherFunctions;
            HelpFunctions.getRandomObject(functions).ifPresent(function -> addToMemory(function.apply(this)));
        }
    }

    /**
     * Reacts on max fullness.
     */
    @Override
    protected void reactMaxFullness() {
        switch (gender) {
            case MALE -> makeRecord(this, "Shit himself");
            case FEMALE -> makeRecord(this, "Shit herself");
        }
        // addToMemory(PersonAPI.takeShower.apply(this));
        memory.add(PersonAPI.takeShower.apply(this));
        fullness = 0;
    }

    @Override
    public String toString() {
        return String.format("%s (%s, %s)", name, gender.toString(), status.toString());
    }

    public Gender getGender() {
        return gender;
    }

    public FamilyStatus getStatus() {
        return status;
    }

    public Map<Event, LocalDateTime> getSolvedEvents() {
        return solvedEvents;
    }
}
