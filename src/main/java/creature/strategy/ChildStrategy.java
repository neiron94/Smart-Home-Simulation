/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package creature.strategy;

import creature.Action;
import creature.person.Person;
import event.*;
import utils.RankedQueue;

/**
 * Strategy for reacting on events as child.
 */
public record ChildStrategy(Person person) implements PersonStrategy {
    @Override
    public void react(BreakEvent event) {
        RankedQueue<Action<Person, ?>> sequence = new RankedQueue<>(event.getPriority());
        sequence.add(new Action<>(1, true, person, event, PersonActions.callAdult));
        person().addToMemory(sequence);
    }

    @Override
    public void react(AlertEvent event) {
        RankedQueue<Action<Person, ?>> sequence = new RankedQueue<>(event.getPriority());
        sequence.add(new Action<>(1, true, person, event, PersonActions.callAdult));
        person().addToMemory(sequence);
    }

    @Override
    public void react(FireEvent event) {
        RankedQueue<Action<Person, ?>> sequence = new RankedQueue<>(event.getPriority());
        sequence.add(new Action<>(1, true, person, event, PersonActions.callAdult));
        sequence.add(new Action<>(10, true, person, null, PersonActions.panic));
        person().addToMemory(sequence);
    }

    @Override
    public void react(FloodEvent event) {
        RankedQueue<Action<Person, ?>> sequence = new RankedQueue<>(event.getPriority());
        sequence.add(new Action<>(1, true, person, event, PersonActions.callAdult));
        person().addToMemory(sequence);
    }

    @Override
    public void react(LeakEvent event) {
        RankedQueue<Action<Person, ?>> sequence = new RankedQueue<>(event.getPriority());
        sequence.add(new Action<>(1, true, person, event, PersonActions.callAdult));
        person().addToMemory(sequence);
    }
}
