/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package place;

/**
 * Type of the room.
 */
public enum RoomType {
    KITCHEN("Kitchen"),
    HALL("Hall"),
    BEDROOM("Bedroom"),
    TOILET("Toilet"),
    SHOWER("Shower"),
    STORAGE("Storage");

    private final String description;

    RoomType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
