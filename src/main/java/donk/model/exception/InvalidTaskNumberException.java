package donk.model.exception;

/**
 * Indicates that a task index is missing or out of range.
 */
public class InvalidTaskNumberException extends InvalidInputException {
    /**
     * Creates an exception with the given message.
     *
     * @param s error message
     */
    public InvalidTaskNumberException(String s) {
        super(s);
    }
}
