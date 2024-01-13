package creature.strategy;

import creature.Action;
import creature.pet.Pet;
import event.Event;
import place.Room;
import java.util.ArrayList;
import java.util.List;

/**
 * Strategy for reacting on events as default Pet.
 */
public record SomePetStrategy(Pet pet) implements PetStrategy {
    @Override
    public List<Action<Pet, Room>> solveAlert(Event event) {
        List<Action<Pet, Room>> sequence = new ArrayList<>();
        sequence.add(new Action<>(1, true, pet, event.getOrigin(), PetActions.noise));
        return sequence;
    }

    @Override
    public List<Action<Pet, Room>> solveLeak(Event event) {
        List<Action<Pet, Room>> sequence = new ArrayList<>();
        sequence.add(new Action<>(1, true, pet, event.getOrigin(), PetActions.noise));
        return sequence;
    }

    @Override
    public List<Action<Pet, Room>> solveFire(Event event) {
        List<Action<Pet, Room>> sequence = new ArrayList<>();
        sequence.add(new Action<>(1, true, pet, event.getOrigin(), PetActions.noise));
        return sequence;
    }
}
