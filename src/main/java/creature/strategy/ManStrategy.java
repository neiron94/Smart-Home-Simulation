package creature.strategy;

import consumer.device.Device;
import creature.Action;
import creature.person.Person;
import event.*;
import place.Room;
import java.util.ArrayList;
import java.util.List;

public record ManStrategy(Person person) implements AdultStrategy {
    @Override
    public List<Action<Person, Room>> solveFlood(Event event) {
        List<Action<Person, Room>> sequence = new ArrayList<>();
        sequence.add(new Action<>(90, true, person, event.getOrigin(), PersonActions.repairWaterSystem));
        return sequence;
    }

    @Override
    public List<Action<Person, Room>> solveLeak(Event event) {
        List<Action<Person, Room>> sequence = new ArrayList<>();
        sequence.add(new Action<>(90, true, person, event.getOrigin(), PersonActions.repairGasSystem));
        return sequence;
    }

    @Override
    public List<Action<Person, Room>> solveFire(Event event) {
        List<Action<Person, Room>> sequence = new ArrayList<>();
        sequence.add(new Action<>(5, true, person, event.getOrigin(), PersonActions.putOutFire));
        return sequence;
    }

    @Override
    public List<Action<Person, Device>> solveBreak(Device device) {
        List<Action<Person, Device>> sequence = new ArrayList<>();
        sequence.add(new Action<>(device.getManual().getDifficulty().getRepairTime() / 10, true, person, device, PersonActions.readManual));
        sequence.add(new Action<>(device.getManual().getDifficulty().getRepairTime(), true, person, device, PersonActions.repairDevice));
        return sequence;
    }
}
