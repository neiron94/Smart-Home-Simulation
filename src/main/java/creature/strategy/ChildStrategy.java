package creature.strategy;

import creature.Action;
import creature.person.Person;
import event.*;
import utils.RankedQueue;

public class ChildStrategy implements PersonStrategy {
    private final Person person;

    public ChildStrategy(Person person) {
        this.person = person;
    }

    @Override
    public Person getPerson() {
        return person;
    }

    @Override
    public void react(BreakEvent event) {
        RankedQueue<Action<Person, ?>> sequence = new RankedQueue<>(event.getPriority());

        // TODO Implement

        getPerson().addToMemory(sequence);
    }

    @Override
    public void react(AlertEvent event) {
        RankedQueue<Action<Person, ?>> sequence = new RankedQueue<>(event.getPriority());

        // TODO Implement

        getPerson().addToMemory(sequence);
    }

    @Override
    public void react(FireEvent event) {
        RankedQueue<Action<Person, ?>> sequence = new RankedQueue<>(event.getPriority());

        // TODO Implement

        getPerson().addToMemory(sequence);
    }

    @Override
    public void react(FloodEvent event) {
        RankedQueue<Action<Person, ?>> sequence = new RankedQueue<>(event.getPriority());

        // TODO Implement

        getPerson().addToMemory(sequence);
    }

    @Override
    public void react(LeakEvent event) {
        RankedQueue<Action<Person, ?>> sequence = new RankedQueue<>(event.getPriority());

        // TODO Implement

        getPerson().addToMemory(sequence);
    }
}
