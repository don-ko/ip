package donk.model.exception;

/**
 * Base exception for invalid user input.
 */
public class InvalidInputException extends Exception {
    /**
     * Creates an exception with the given message.
     *
     * @param s error message.
     */
    public InvalidInputException(String s) {
        super(s);
    }
}
