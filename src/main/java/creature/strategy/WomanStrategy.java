package creature.strategy;

import creature.Action;
import creature.person.Person;
import event.*;
import place.Room;

public class WomanStrategy implements AdultStrategy { // TODO Check conversion to record after implementation
    private final Person person;

    public WomanStrategy(Person person) {
        this.person = person;
    }

    @Override
    public Person getPerson() {
        return person;
    }

    @Override
    public Action<Person, Room> solveFlood(Event event) {
        return new Action<>(180, false, getPerson(), event.getOrigin(), Actions.callWaterService);
    }

    @Override
    public Action<Person, Room> solveLeak(Event event) {
        return new Action<>(180, false, getPerson(), event.getOrigin(), Actions.callGasService);
    }

    @Override
    public Action<Person, Room> solveFire(Event event) {
        return new Action<>(10, false, getPerson(), event.getOrigin(), Actions.callFireman);
    }
}
