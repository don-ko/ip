package donk.model.exception;

/**
 * Indicates invalid arguments for a valid command.
 */
public class InvalidArgumentException extends InvalidInputException {
    /**
     * Creates an exception with the given message.
     *
     * @param s error message
     */
    public InvalidArgumentException(String s) {
        super(s);
    }
}
