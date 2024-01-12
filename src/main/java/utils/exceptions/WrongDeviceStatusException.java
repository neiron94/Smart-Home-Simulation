package utils.exceptions;

/**
 * Signals that device is not in its start status and can't be used
 * until turning it on (on means start status).
 */
public class WrongDeviceStatusException extends Exception {
    public WrongDeviceStatusException() {
    }

    public WrongDeviceStatusException(String message) {
        super(message);
    }
}
