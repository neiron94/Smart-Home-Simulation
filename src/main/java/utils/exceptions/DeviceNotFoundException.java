/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package utils.exceptions;

/**
 * Signals that searched device is not found.
 */
public class DeviceNotFoundException extends Exception {
    public DeviceNotFoundException() {
    }

    public DeviceNotFoundException(String message) {
        super(message);
    }
}
