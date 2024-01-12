package utils.exceptions;

/**
 * Signals that filter of some device should be cleaned.
 */
public class DirtyFilterException extends Exception {
    public DirtyFilterException() {
    }

    public DirtyFilterException(String message) {
        super(message);
    }
}
