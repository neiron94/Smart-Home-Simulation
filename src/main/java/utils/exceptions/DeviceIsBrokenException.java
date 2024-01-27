/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package utils.exceptions;

/**
 * Signals that device is broken and can't be used.
 */
public class DeviceIsBrokenException extends Exception {
    public DeviceIsBrokenException() {
    }

    public DeviceIsBrokenException(String message) {
        super(message);
    }
}
