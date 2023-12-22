package creature.person;

import creature.Creature;

public class Person extends Creature {
    private final Gender gender;
    private final FamilyStatus status;
    private List<Event> solvedEvents;
    // TODO Additional parameters can be added

    public Person(String name, Room room, Gender gender, FamilyStatus status) { // TODO Control after creating Room class
        super(name, room);
        this.gender = gender;
        this.status = status;
    }

    public Gender getGender() {
        return gender;
    }

    public FamilyStatus getStatus() {
        return status;
    }

    public List<Event> getSolvedEvents() {
        return solvedEvents;
    }

    public void addSolvedEvent(Event solvedEvent) { // TODO Control after Creation Event class
        solvedEvents.add(solvedEvent);
    }

    @Override
    public String toString() {
        return "Person " + name;
    }
}
