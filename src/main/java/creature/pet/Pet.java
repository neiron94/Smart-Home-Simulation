package creature.pet;

import creature.Action;
import creature.Creature;
import creature.person.Person;
import creature.person.PersonAPI;
import creature.strategy.*;
import place.Room;
import smarthome.Simulation;
import utils.HelpFunctions;
import utils.RankedQueue;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

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
        List<Function<Pet, RankedQueue<Action<Pet, ?>>>> functions = List.of(PetAPI.sleep, PetAPI.play, PetAPI.goToBackyard, PetAPI.goToBackyard);
        functions.stream().findAny().ifPresent(function -> memory.add(function.apply(this)));
    }

    @Override
    protected void reactMaxFullness() {
        activity.addActivity("Shitted");
        Simulation.getInstance().getCreatures().stream()
                .filter(creature -> creature instanceof Person)
                .map(person -> (Person) person)
                .filter(person -> person.isAlive() && !person.isBusy() && person.isAtHome())
                .findFirst().ifPresent(human -> new Action<>(1, true, human, null, PersonAPI.cleanAfterPet).perform());
        fullness = 0;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, type.toString());
    }
}
