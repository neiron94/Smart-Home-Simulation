/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package utils.exceptions;

/**
 * Signals that some resource (water, electricity or gas) is not available now.
 */
public class ResourceNotAvailableException extends Exception {
    public ResourceNotAvailableException() {
    }

    public ResourceNotAvailableException(String message) {
        super(message);
    }
}
