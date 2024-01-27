/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package utils.exceptions;

/**
 * Signals that device is totally broken and can't be used or repaired.
 */
public class NotRepairableDeviceException extends Exception {
    public NotRepairableDeviceException() {
    }

    public NotRepairableDeviceException(String message) {
        super(message);
    }
}
