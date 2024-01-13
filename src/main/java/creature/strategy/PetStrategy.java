package creature.strategy;

import creature.Action;
import creature.pet.Pet;
import event.*;
import place.Room;
import utils.HelpFunctions;
import utils.RankedQueue;
import java.util.List;
import java.util.function.Function;

/**
 * Strategy for reacting on events as Pet.
 */
public interface PetStrategy extends Strategy {
    Pet pet();

    List<Action<Pet, Room>> solveAlert(Event event);
    List<Action<Pet, Room>> solveLeak(Event event);
    List<Action<Pet, Room>> solveFire(Event event);

    @Override
    default void react(WakeUpEvent event) {
        RankedQueue<Action<Pet, ?>> sequence = new RankedQueue<>(event.getPriority());
        sequence.add(new Action<>(5, true, pet(), event.getCreator().getRoom(), PetActions.wakeMasterUp));
        pet().addToMemory(sequence);
    }

    @Override
    default void react(FillEvent event) {}

    @Override
    default void react(BreakEvent event) {}

    @Override
    default void react(AlertEvent event) {
        RankedQueue<Action<Pet, ?>> sequence = new RankedQueue<>(event.getPriority());
        sequence.addAll(solveAlert(event));
        sequence.add(new Action<>(1, true, pet(), null, PetActions.panic));
        pet().addToMemory(sequence);
    }

    @Override
    default void react(FireEvent event) {
        RankedQueue<Action<Pet, ?>> sequence = new RankedQueue<>(event.getPriority());
        sequence.addAll(solveFire(event));
        sequence.add(new Action<>(1, true, pet(), null, PetActions.panic));
        pet().addToMemory(sequence);
    }

    @Override
    default void react(FloodEvent event) {
        RankedQueue<Action<Pet, ?>> sequence = new RankedQueue<>(event.getPriority());
        sequence.add(new Action<>(1, true, pet(), event.getOrigin(), PetActions.drink));
        pet().addToMemory(sequence);
    }

    @Override
    default void react(LeakEvent event) {
        RankedQueue<Action<Pet, ?>> sequence = new RankedQueue<>(event.getPriority());
        sequence.addAll(solveLeak(event));
        sequence.add(new Action<>(1, true, pet(), null, PetActions.panic));
        pet().addToMemory(sequence);
    }

    interface PetActions {
        Function<Action<Pet, Room>, Boolean> wakeMasterUp = action -> {
            HelpFunctions.makeRecord(action.getExecutor(), String.format("Wake up master in %s", action.getSubject()));
            return true;
        };

        Function<Action<Pet, Room>, Boolean> drink = action -> {
            HelpFunctions.makeRecord(action.getExecutor(), String.format("Drink from flood puddle in %s", action.getSubject()));
            return true;
        };

        Function<Action<Pet, Void>, Boolean> panic = action -> {
            HelpFunctions.makeRecord(action.getExecutor(), "Panic");
            return true;
        };

        Function<Action<Pet, Room>, Boolean> bark = action -> {
            HelpFunctions.makeRecord(action.getExecutor(), String.format("Loudly bark in %s", action.getSubject()));
            return true;
        };

        Function<Action<Pet, Room>, Boolean> meow = action -> {
            HelpFunctions.makeRecord(action.getExecutor(), String.format("Loudly meow in %s", action.getSubject()));
            return true;
        };

        Function<Action<Pet, Room>, Boolean> noise = action -> {
            HelpFunctions.makeRecord(action.getExecutor(), String.format("Make loud nose in %s", action.getSubject()));
            return true;
        };
    }
}
