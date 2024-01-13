package creature.strategy;

import consumer.device.Device;
import creature.Action;
import creature.person.Person;
import event.*;
import place.Room;
import java.util.ArrayList;
import java.util.List;

/**
 * Strategy for reacting on events as woman.
 */
public record WomanStrategy(Person person) implements AdultStrategy {
    @Override
    public List<Action<Person, Room>> solveFlood(Event event) {
        List<Action<Person, Room>> sequence = new ArrayList<>();
        sequence.add(new Action<>(180, false, person, event.getOrigin(), PersonActions.callWaterService));
        return sequence;
    }

    @Override
    public List<Action<Person, Room>> solveLeak(Event event) {
        List<Action<Person, Room>> sequence = new ArrayList<>();
        sequence.add(new Action<>(180, false, person, event.getOrigin(), PersonActions.callGasService));
        return sequence;
    }

    @Override
    public List<Action<Person, Room>> solveFire(Event event) {
        List<Action<Person, Room>> sequence = new ArrayList<>();
        sequence.add(new Action<>(10, false, person, event.getOrigin(), PersonActions.callFireman));
        return sequence;
    }

    @Override
    public List<Action<Person, Device>> solveBreak(Device device) {
        List<Action<Person, Device>> sequence = new ArrayList<>();
        sequence.add(new Action<>(5, true, person, device, PersonActions.buyNewDevice));
        return sequence;
    }
}
