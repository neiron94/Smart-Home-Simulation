package utils.exceptions;

public class WrongDeviceStatusException extends Exception {
    public WrongDeviceStatusException() {
    }

    public WrongDeviceStatusException(String message) {
        super(message);
    }
}
