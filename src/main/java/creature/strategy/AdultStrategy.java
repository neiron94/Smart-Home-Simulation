package creature.strategy;

import consumer.device.Device;
import consumer.device.sensored.Alarm;
import creature.Action;
import creature.person.Person;
import event.*;
import place.Room;
import utils.RankedQueue;
import java.util.List;

public interface AdultStrategy extends PersonStrategy {
    List<Action<Person, Room>> solveFlood(Event event);
    List<Action<Person, Room>> solveLeak(Event event);
    List<Action<Person, Room>> solveFire(Event event);
    List<Action<Person, Device>> solveBreak(Device device);

    @Override
    default void react(AlertEvent event) {
        RankedQueue<Action<Person, ?>> sequence = new RankedQueue<>(event.getPriority());

        sequence.add(new Action<>(1, true, person(), event.getOrigin(), PersonActions.goToRoom));
        sequence.add(new Action<>(0, true, person(), event, PersonActions.takeEvent));
        sequence.add(new Action<>(1, true, person(), (Alarm<?>) event.getCreator(), PersonActions.stopAlarm));
        sequence.add(new Action<>(0, true, person(), event, PersonActions.recordEvent));

        person().addToMemory(sequence);
    }

    @Override
    default void react(FireEvent event) {
        RankedQueue<Action<Person, ?>> sequence = new RankedQueue<>(event.getPriority());

        sequence.add(new Action<>(0, true, person(), event, PersonActions.takeEvent));
        sequence.addAll(solveFire(event));
        sequence.add(new Action<>(0, true, person(), event, PersonActions.recordEvent));

        person().addToMemory(sequence);
    }

    @Override
    default void react(FloodEvent event) {
        RankedQueue<Action<Person, ?>> sequence = new RankedQueue<>(event.getPriority());

        sequence.add(new Action<>(0, true, person(), event, PersonActions.takeEvent));
        sequence.add(new Action<>(1, true, person(), event.getOrigin(), PersonActions.turnOffWater));
        sequence.add(new Action<>(1, true, person(), event.getOrigin(), PersonActions.turnOffElectricity));
        sequence.addAll(solveFlood(event));
        sequence.add(new Action<>(10, true, person(), event.getOrigin(), PersonActions.cleanFloor));
        sequence.add(new Action<>(1, true, person(), event.getOrigin(), PersonActions.turnOnElectricity));
        sequence.add(new Action<>(1, true, person(), event.getOrigin(), PersonActions.turnOnWater));
        sequence.add(new Action<>(0, true, person(), event, PersonActions.recordEvent));

        person().addToMemory(sequence);
    }

    @Override
    default void react(LeakEvent event) {
        RankedQueue<Action<Person, ?>> sequence = new RankedQueue<>(event.getPriority());

        sequence.add(new Action<>(0, true, person(), event, PersonActions.takeEvent));
        sequence.add(new Action<>(1, true, person(), event.getOrigin(), PersonActions.turnOffGas));
        sequence.add(new Action<>(1, true, person(), event.getOrigin(), PersonActions.turnOffElectricity));
        sequence.addAll(solveLeak(event));
        sequence.add(new Action<>(1, true, person(), event.getOrigin(), PersonActions.turnOnElectricity));
        sequence.add(new Action<>(1, true, person(), event.getOrigin(), PersonActions.turnOnGas));
        sequence.add(new Action<>(0, true, person(), event, PersonActions.recordEvent));

        person().addToMemory(sequence);
    }

    default void react(BreakEvent event) {
        RankedQueue<Action<Person, ?>> sequence = new RankedQueue<>(event.getPriority());

        sequence.add(new Action<>(0, true, person(), event, PersonActions.takeEvent));
        sequence.add(new Action<>(30, true, person(), event.getCreator(), PersonActions.findManual));

        if (new Action<>(0, true, person(), event.getCreator(), PersonActions.checkWarranty).perform()) {
            sequence.add(new Action<>(30, true, person(), event.getCreator(), PersonActions.bringDeviceToService));
            sequence.add(new Action<>(event.getCreator().getManual().getDifficulty().getRepairTime() * 10, false, person(), event.getCreator(), PersonActions.takeDeviceFromService));
        } else {
            sequence.addAll(solveBreak(event.getCreator()));
        }

        sequence.add(new Action<>(0, true, person(), event, PersonActions.recordEvent));

        person().addToMemory(sequence);
    }
}
