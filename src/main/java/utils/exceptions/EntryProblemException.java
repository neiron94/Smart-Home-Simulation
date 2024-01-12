package utils.exceptions;

/**
 * Signals that device does not have entry to work with.
 */
public class EntryProblemException extends Exception {
    public EntryProblemException() {
    }

    public EntryProblemException(String message) {
        super(message);
    }
}
