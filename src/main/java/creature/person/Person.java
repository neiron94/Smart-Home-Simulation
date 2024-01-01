package creature.person;

import creature.Creature;
import event.Event;
import smarthome.Simulation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class Person extends Creature {
    private final Gender gender;
    private final FamilyStatus status;
    private Map<Event, LocalDateTime> solvedEvents;
    // TODO Additional parameters can be added

    public Person(String name, Gender gender, FamilyStatus status) {
        super(name);
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
    public String toString() {
        return String.format("%s (%s, %s)", name, gender.toString(), status.toString());
    }
}
