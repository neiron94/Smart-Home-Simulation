package creature;

import creature.person.*;
import creature.pet.*;
import java.util.NoSuchElementException;


public class CreatureFactory {
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

        return new Person(name, gender, status);
    }

    public Pet createPet(String name, String petType) {
        PetType type;

        try {
            type = PetType.valueOf(petType);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException("Invalid Pet Type");
        }

        return new Pet(name, type);
    }
}
