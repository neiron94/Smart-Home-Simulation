package creature.person;

import creature.Creature;
import event.Event;
import place.Location;
import smarthome.Simulation;
import java.time.LocalDateTime;
import java.util.Map;

public class Person extends Creature {
    private final Gender gender;
    private final FamilyStatus status;
    private Map<Event, LocalDateTime> solvedEvents;

    public Person(String name, Gender gender, FamilyStatus status, Location startLocation) {
        super(name, startLocation);
        this.gender = gender;
        this.status = status;
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
    public void routine() {
        // TODO Implement person routine
    }

    @Override
    public String toString() {
        return String.format("%s (%s, %s)", name, gender.toString(), status.toString());
    }
}
