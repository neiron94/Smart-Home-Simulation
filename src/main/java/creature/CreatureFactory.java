package creature;

import creature.person.*;
import creature.pet.*;
import place.Home;
import place.Room;
import smarthome.Simulation;
import utils.HelpFunctions;
import java.util.List;
import java.util.NoSuchElementException;

public class CreatureFactory {
    private final List<Room> locations = Simulation.getInstance().getHome().getFloors().stream().flatMap(floor -> floor.getRooms().stream()).toList();;

    public Person createPerson(String name, String personGender, String personStatus) {
        Gender gender;
        FamilyStatus status;

        try {
            gender = Gender.valueOf(personGender);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException("Invalid Gender");
        }

        try {
            status = FamilyStatus.valueOf(personStatus);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException("Invalid Family Status");
        }

        return new Person(name, gender, status, HelpFunctions.getRandomObject(locations));
    }

    public Pet createPet(String name, String petType) {
        PetType type;

        try {
            type = PetType.valueOf(petType);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException("Invalid Pet Type");
        }

        return new Pet(name, type, HelpFunctions.getRandomObject(locations));
    }
}
