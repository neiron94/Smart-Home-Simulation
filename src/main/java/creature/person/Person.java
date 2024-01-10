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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class Person extends Creature {
    private final Gender gender;
    private final FamilyStatus status;
    private final Map<Event, LocalDateTime> solvedEvents;

    public Person(String name, Gender gender, FamilyStatus status, Room startRoom) {
        super(name, startRoom);
        this.gender = gender;
        this.status = status;
        solvedEvents = new HashMap<>();

        if (status == FamilyStatus.KID) strategy = new ChildStrategy(this);
        else strategy = gender == Gender.MALE ? new ManStrategy(this) : new WomanStrategy(this);
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

    public void addSolvedEvent(Event solvedEvent) {
        solvedEvents.put(solvedEvent, Simulation.getInstance().getCurrentTime());
    }

    @Override
    protected void decreaseHunger() {
        DayPeriod period = HelpFunctions.getDayPeriod(Simulation.getInstance().getCurrentTime()).orElse(DayPeriod.NIGHT);
        switch (period) {
            case MORNING -> memory.add(PersonAPI.takeBreakfast.apply(this));
            case DAY -> memory.add(PersonAPI.takeLunch.apply(this));
            case EVENING -> memory.add(PersonAPI.takeDinner.apply(this));
        }
    }

    @Override
    protected void decreaseFullness() {
        List<Function<Person, RankedQueue<Action<Person, ?>>>> functions = List.of(PersonAPI.pee, PersonAPI.poo);
        if (fullness > 0) memory.add(PersonAPI.poo.apply(this)); // TODO Constant
        else memory.add(Objects.requireNonNull(HelpFunctions.getRandomObject(functions)).apply(this));
    }

    @Override
    protected void chooseActivity() {
        // TODO Implement
    }

    @Override
    protected void reactMaxFullness() {
        switch (gender) {
            case MALE -> activity.addActivity("Shit himself");
            case FEMALE -> activity.addActivity("Shit herself");
        }
        memory.add(PersonAPI.takeShower.apply(this));
        fullness = 0;
    }

    @Override
    public String toString() {
        return String.format("%s (%s, %s)", name, gender.toString(), status.toString());
    }
}
