package creature.strategy;

import creature.Action;
import creature.person.Person;
import event.*;
import place.Room;

public class ManStrategy implements AdultStrategy { // TODO Check conversion to record after implementation
    private final Person person;

    public ManStrategy(Person person) {
        this.person = person;
    }

    @Override
    public Person getPerson() {
        return person;
    }

    @Override
    public Action<Person, Room> solveFlood(Event event) {
        return new Action<>(90, true, getPerson(), event.getOrigin(), Actions.repairWaterSystem);
    }

    @Override
    public Action<Person, Room> solveLeak(Event event) {
        return new Action<>(90, true, getPerson(), event.getOrigin(), Actions.repairGasSystem);
    }

    @Override
    public Action<Person, Room> solveFire(Event event) {
        return new Action<>(5, true, getPerson(), event.getOrigin(), Actions.putOutFire);
    }
}
