package creature.pet;

import creature.Action;
import creature.Creature;
import creature.person.Person;
import creature.person.PersonAPI;
import creature.strategy.*;
import place.Room;
import smarthome.Simulation;

public class Pet extends Creature {
    private final PetType type;

    public Pet(String name, PetType type, Room startRoom) {
        super(name, startRoom);
        this.type = type;

        switch (type) {
            case CAT -> strategy = new CatStrategy(this);
            case DOG -> strategy = new DogStrategy(this);
            default -> strategy = new SomePetStrategy(this);
        }
    }

    public PetType getType() {
        return type;
    }

    @Override
    protected void decreaseHunger() {
        memory.add(PetAPI.feed.apply(this));
    }

    @Override
    protected void decreaseFullness() {
        memory.add(PetAPI.goToToilet.apply(this));
    }

    @Override
    protected void chooseActivity() {
        // TODO Implement
    }

    @Override
    protected void reactMaxFullness() {
        activity.addActivity("Shitted");
        Simulation.getInstance().getCreatures().stream()
                .filter(creature -> creature instanceof Person)
                .map(person -> (Person) person)
                .filter(person -> !person.isBusy() && person.isAlive())
                .findFirst().ifPresent(human -> new Action<>(0, true, human, null, PersonAPI.cleanAfterPet).perform());
        fullness = 0;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, type.toString());
    }
}
