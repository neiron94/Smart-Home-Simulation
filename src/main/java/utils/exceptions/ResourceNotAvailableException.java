package utils.exceptions;

/**
 * Signals that some resource (water, electricity or gas) is not available now.
 */
public class ResourceNotAvailableException extends Exception {
    public ResourceNotAvailableException() {
    }

    public ResourceNotAvailableException(String message) {
        super(message);
    }
}
