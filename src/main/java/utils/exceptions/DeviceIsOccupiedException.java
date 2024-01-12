package utils.exceptions;

/**
 * Signals that device is occupied by someone else and can't be used.
 */
public class DeviceIsOccupiedException extends Exception {
    public DeviceIsOccupiedException() {
    }

    public DeviceIsOccupiedException(String message) {
        super(message);
    }
}
