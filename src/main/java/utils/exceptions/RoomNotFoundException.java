package utils.exceptions;

public class RoomNotFoundException extends Exception {
    public RoomNotFoundException() {
    }

    public RoomNotFoundException(String message) {
        super(message);
    }
}
