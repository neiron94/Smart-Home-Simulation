package creature.strategy;

import creature.Action;
import creature.pet.Pet;
import event.Event;
import place.Room;
import java.util.ArrayList;
import java.util.List;

public record DogStrategy(Pet pet) implements PetStrategy {
    @Override
    public List<Action<Pet, Room>> solveAlert(Event event) {
        List<Action<Pet, Room>> sequence = new ArrayList<>();
        sequence.add(new Action<>(1, true, pet, event.getOrigin(), PetActions.bark));
        return sequence;
    }

    @Override
    public List<Action<Pet, Room>> solveLeak(Event event) {
        List<Action<Pet, Room>> sequence = new ArrayList<>();
        sequence.add(new Action<>(1, true, pet, event.getOrigin(), PetActions.bark));
        return sequence;
    }

    @Override
    public List<Action<Pet, Room>> solveFire(Event event) {
        List<Action<Pet, Room>> sequence = new ArrayList<>();
        sequence.add(new Action<>(1, true, pet, event.getOrigin(), PetActions.bark));
        return sequence;
    }
}
