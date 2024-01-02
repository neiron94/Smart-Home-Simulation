package creature.pet;

import creature.Creature;
import place.Location;

public class Pet extends Creature {
    private final PetType type;

    public Pet(String name, PetType type, Location startLocation) {
        super(name, startLocation);
        this.type = type;
    }

    public PetType getType() {
        return type;
    }

    @Override
    public void routine() {
        // TODO Implement pet routine
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, type.toString());
    }
}
