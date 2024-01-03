package utils.exceptions;

public class DeviceIsBrokenException extends Exception {
    public DeviceIsBrokenException() {
    }

    public DeviceIsBrokenException(String message) {
        super(message);
    }
}
