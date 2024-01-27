/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package utils.exceptions;

/**
 * Signals that searched room is not found.
 */
public class RoomNotFoundException extends Exception {
    public RoomNotFoundException() {
    }

    public RoomNotFoundException(String message) {
        super(message);
    }
}
