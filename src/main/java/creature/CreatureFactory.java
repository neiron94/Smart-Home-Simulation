/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package creature;

import creature.person.*;
import creature.pet.*;
import place.Room;
import smarthome.Simulation;
import utils.HelpFunctions;
import java.util.List;

/**
 * Factory for creating creatures.
 */
public class CreatureFactory {
    private final List<Room> rooms = Simulation.getInstance().getHome().getFloors().stream().flatMap(floor -> floor.getRooms().stream()).toList();

    /**
     * Creation method. Return type is void, because person automatically
     * adds itself to simulation in the constructor.
     * @param name person's name
     * @param personGender person's gender
     * @param personStatus person's family status
     */
    public void createPerson(String name, String personGender, String personStatus) {
        Gender gender;
        FamilyStatus status;

        try {
            gender = Gender.valueOf(personGender);
        } catch (IllegalArgumentException e) {
            HelpFunctions.logger.warn(String.format("Invalid person gender '%s'. Person %s wasn't created", personGender, name));
            return;
        }

        try {
            status = FamilyStatus.valueOf(personStatus);
        } catch (IllegalArgumentException e) {
            HelpFunctions.logger.warn(String.format("Invalid person family status '%s'. Person %s wasn't created", personStatus, name));
            return;
        }

        HelpFunctions.getRandomObject(rooms).ifPresent(room -> {
            Person person = new Person(name, gender, status, room);
            HelpFunctions.logger.info(String.format("%s was created in %s", person, room));
        });
    }

    /**
     * Creation method. Return type is void, because pet automatically
     * adds itself to simulation in the constructor.
     * @param name pet's name
     * @param petType type of pet
     */
    public void createPet(String name, String petType) {
        PetType type;

        try {
            type = PetType.valueOf(petType);
        } catch (IllegalArgumentException e) {
            HelpFunctions.logger.warn(String.format("Invalid pet type '%s'. Pet %s wasn't created", petType, name));
            return;
        }

        HelpFunctions.getRandomObject(rooms).ifPresent(room -> {
            Pet pet = new Pet(name, type, room);
            HelpFunctions.logger.info(String.format("%s was created in %s", pet, room));
        });
    }
}
