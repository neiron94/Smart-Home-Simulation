package utils.exceptions;

/**
 * Signals that searched room is not found.
 */
public class RoomNotFoundException extends Exception {
    public RoomNotFoundException() {
    }

    public RoomNotFoundException(String message) {
        super(message);
    }
}
