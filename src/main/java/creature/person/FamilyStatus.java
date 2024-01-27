/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package creature.person;

/**
 * Family status.
 */
public enum FamilyStatus {
    KID("Kid"),
    ADULT("Adult"),
    ELDER("Elder");

    private final String description;

    FamilyStatus(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
