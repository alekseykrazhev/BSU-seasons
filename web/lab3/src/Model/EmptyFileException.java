package Model;

/**
 * Custom class to throw exception.
 */
public class EmptyFileException extends Exception {

    /**
     * Simple empty file exception with message
     * @param message - message
     */
    public EmptyFileException(String message) {
        super(message);
    }
}
