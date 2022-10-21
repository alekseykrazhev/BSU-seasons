package Model;

/**
 * Custom class to throw exception.
 */
public class TextException extends Exception {

    /**
     * Simple text exception with message.
     * @param message - message
     */
    public TextException(String message) {
        super(message);
    }
}
