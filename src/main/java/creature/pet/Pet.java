package creature.pet;

import creature.Creature;
import place.Room;

public class Pet extends Creature {
    private final PetType type;
    // TODO Additional parameters can be added

    public Pet(String name, PetType type) {
        super(name);
        this.type = type;
    }

    public PetType getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, type.toString());
    }
}
