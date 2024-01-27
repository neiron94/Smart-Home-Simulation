/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package creature.pet;

/**
 * Type of pet.
 */
public enum PetType {
    CAT("Cat"),
    DOG("Dog"),
    RACOON("Racoon"),
    MINI_PIG("Mini pig");

    private final String description;

    PetType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
