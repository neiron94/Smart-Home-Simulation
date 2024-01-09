package creature.pet;

import creature.Action;
import creature.Creature;
import creature.strategy.PetStrategy;
import place.Room;
import utils.RankedQueue;

public class Pet extends Creature {
    private final PetType type;

    public Pet(String name, PetType type, Room startRoom) {
        super(name, startRoom);
        this.type = type;
        strategy = new PetStrategy(this);
    }

    public PetType getType() {
        return type;
    }

    @Override
    protected void decreaseHunger() {
        // TODO Implement
        // TODO After successfully actions planning set isBusy to true
    }

    @Override
    protected void decreaseFullness() {
        // TODO Implement
        // TODO After successfully actions planning set isBusy to true
    }

    @Override
    protected void chooseActivity() {
        // TODO Implement
        // TODO After successfully actions planning set isBusy to first action busy (memory.first().peek().isBusy();)
    }

    @Override
    protected void reactMaxFullness() {
        activity.addActivity("Shitted");
        // TODO Clean after pet
        fullness = 0;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, type.toString());
    }
}
