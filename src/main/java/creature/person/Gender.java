/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package creature.person;

/**
 * Human's gender.
 */
public enum Gender {
    MALE("Male"),
    FEMALE("Female");

    private final String description;

    Gender(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
