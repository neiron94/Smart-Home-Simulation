package utils.exceptions;

public class DeviceNotFoundException extends Exception {
    public DeviceNotFoundException() {
    }

    public DeviceNotFoundException(String message) {
        super(message);
    }
}
