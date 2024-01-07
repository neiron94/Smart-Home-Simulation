package creature.person;

import creature.Creature;
import event.Event;
import place.Room;
import smarthome.Simulation;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Person extends Creature {
    private final Gender gender;
    private final FamilyStatus status;
    private final Map<Event, LocalDateTime> solvedEvents;

    public Person(String name, Gender gender, FamilyStatus status, Room startRoom) {
        super(name, startRoom);
        this.gender = gender;
        this.status = status;
        solvedEvents = new HashMap<>();
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
        // TODO Implement
    }

    @Override
    protected void decreaseFullness() {
        // TODO Implement
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
        fullness = 0;
    }

    @Override
    public String toString() {
        return String.format("%s (%s, %s)", name, gender.toString(), status.toString());
    }
}
