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
