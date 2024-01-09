package creature.strategy;

import creature.Action;
import creature.person.Person;
import event.*;
import place.Room;
import smarthome.Simulation;
import utils.RankedQueue;
import java.util.function.Function;

public interface AdultStrategy extends PersonStrategy {
    Action<Person, Room> solveFlood(Event event);
    Action<Person, Room> solveLeak(Event event);
    Action<Person, Room> solveFire(Event event);

    @Override
    default void react(AlertEvent event) {
        RankedQueue<Action<Person, ?>> sequence = new RankedQueue<>(event.getPriority());

        // TODO Implement

        getPerson().addToMemory(sequence);
    }

    @Override
    default void react(FireEvent event) {
        RankedQueue<Action<Person, ?>> sequence = new RankedQueue<>(event.getPriority());

        sequence.add(new Action<>(0, true, getPerson(), event, Actions.takeEvent));
        sequence.add(solveFire(event));
        sequence.add(new Action<>(0, true, getPerson(), event, Actions.recordEvent));

        getPerson().addToMemory(sequence);
    }

    @Override
    default void react(FloodEvent event) {
        RankedQueue<Action<Person, ?>> sequence = new RankedQueue<>(event.getPriority());

        sequence.add(new Action<>(0, true, getPerson(), event, Actions.takeEvent));
        sequence.add(new Action<>(1, true, getPerson(), event.getOrigin(), Actions.turnOffElectricity));
        sequence.add(new Action<>(1, true, getPerson(), event.getOrigin(), Actions.turnOffWater));
        sequence.add(solveFlood(event));
        sequence.add(new Action<>(10, true, getPerson(), event.getOrigin(), Actions.cleanFloor));
        sequence.add(new Action<>(1, true, getPerson(), event.getOrigin(), Actions.turnOnWater));
        sequence.add(new Action<>(1, true, getPerson(), event.getOrigin(), Actions.turnOnElectricity));
        sequence.add(new Action<>(0, true, getPerson(), event, Actions.recordEvent));

        getPerson().addToMemory(sequence);
    }

    @Override
    default void react(LeakEvent event) {
        RankedQueue<Action<Person, ?>> sequence = new RankedQueue<>(event.getPriority());

        sequence.add(new Action<>(0, true, getPerson(), event, Actions.takeEvent));
        sequence.add(new Action<>(1, true, getPerson(), event.getOrigin(), Actions.turnOffElectricity));
        sequence.add(new Action<>(1, true, getPerson(), event.getOrigin(), Actions.turnOffGas));
        sequence.add(solveLeak(event));
        sequence.add(new Action<>(1, true, getPerson(), event.getOrigin(), Actions.turnOnGas));
        sequence.add(new Action<>(1, true, getPerson(), event.getOrigin(), Actions.turnOnElectricity));
        sequence.add(new Action<>(0, true, getPerson(), event, Actions.recordEvent));

        getPerson().addToMemory(sequence);
    }

    default void react(BreakEvent event) {
        RankedQueue<Action<Person, ?>> sequence = new RankedQueue<>(event.getPriority());

        // TODO Implement

        getPerson().addToMemory(sequence);
    }
}
