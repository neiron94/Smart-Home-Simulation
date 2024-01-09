package creature.strategy;

import creature.Action;
import creature.pet.Pet;
import event.*;
import utils.RankedQueue;

public class PetStrategy implements Strategy {
    private final Pet pet;

    public PetStrategy(Pet pet) {
        this.pet = pet;
    }

    @Override
    public void react(WakeUpEvent event) {
        RankedQueue<Action<Pet, ?>> sequence = new RankedQueue<>(event.getPriority());

        // TODO Implement

        pet.addToMemory(sequence);
    }

    @Override
    public void react(FillEvent event) {
        RankedQueue<Action<Pet, ?>> sequence = new RankedQueue<>(event.getPriority());

        // TODO Implement

        pet.addToMemory(sequence);
    }

    @Override
    public void react(BreakEvent event) {
        RankedQueue<Action<Pet, ?>> sequence = new RankedQueue<>(event.getPriority());

        // TODO Implement

        pet.addToMemory(sequence);
    }

    @Override
    public void react(AlertEvent event) {
        RankedQueue<Action<Pet, ?>> sequence = new RankedQueue<>(event.getPriority());

        // TODO Implement

        pet.addToMemory(sequence);
    }

    @Override
    public void react(FireEvent event) {
        RankedQueue<Action<Pet, ?>> sequence = new RankedQueue<>(event.getPriority());

        // TODO Implement

        pet.addToMemory(sequence);
    }

    @Override
    public void react(FloodEvent event) {
        RankedQueue<Action<Pet, ?>> sequence = new RankedQueue<>(event.getPriority());

        // TODO Implement

        pet.addToMemory(sequence);
    }

    @Override
    public void react(LeakEvent event) {
        RankedQueue<Action<Pet, ?>> sequence = new RankedQueue<>(event.getPriority());

        // TODO Implement

        pet.addToMemory(sequence);
    }
}
