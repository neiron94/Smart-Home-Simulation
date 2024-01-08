package utils.exceptions;

public class DeviceIsOccupiedException extends Exception {
    public DeviceIsOccupiedException() {
    }

    public DeviceIsOccupiedException(String message) {
        super(message);
    }
}
