/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package utils.exceptions;

/**
 * Signals that filter of some device should be cleaned.
 */
public class DirtyFilterException extends Exception {
    public DirtyFilterException() {
    }

    public DirtyFilterException(String message) {
        super(message);
    }
}
