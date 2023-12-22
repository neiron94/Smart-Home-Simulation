package creature.pet;

import creature.Creature;
import place.Room;

public class Pet extends Creature {
    private final PetType type;
    // TODO Additional parameters can be added

    public Pet(String name, Room room, PetType type) {
        super(name, room);
        this.type = type;
    }

    public PetType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.toString() + " " + name;
    }
}
