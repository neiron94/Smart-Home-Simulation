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

import java.util.*;
import java.util.function.Function;

import static utils.HelpFunctions.makeRecord;

/**
 * Pet cannot interact with devices (except for feeder), but can react on events.
 */
public class Pet extends Creature {
    private final PetType type;

    /**
     * Creates new pet.
     * @param name name of pet
     * @param type type of pet
     * @param startRoom room where pet is located
     */
    public Pet(String name, PetType type, Room startRoom) {
        super(name, startRoom);
        this.type = type;

        switch (type) {
            case CAT -> strategy = new CatStrategy(this);
            case DOG -> strategy = new DogStrategy(this);
            default -> strategy = new SomePetStrategy(this);
        }
    }

    /**
     * Chooses action for decreasing hunger.
     */
    @Override
    protected void decreaseHunger() {
        addToMemory(PetAPI.feed.apply(this));
    }

    /**
     * Chooses action for decreasing fullness.
     */
    @Override
    protected void decreaseFullness() {
        addToMemory(PetAPI.goToToilet.apply(this));
    }

    /**
     * Chooses new action.
     */
    @Override
    protected void chooseActivity() {
        List<Function<Pet, RankedQueue<Action<Pet, ?>>>> functions = List.of(PetAPI.sleep, PetAPI.play, PetAPI.goToBackyard, PetAPI.brakeDevice);
        HelpFunctions.getRandomObject(functions).ifPresent(function -> addToMemory(function.apply(this)));
    }

    /**
     * Reacts on max fullness.
     */
    @Override
    protected void reactMaxFullness() {
        makeRecord(this, "Shitted");
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

    public PetType getType() {
        return type;
    }
}
