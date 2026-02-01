package donk.model.exception;

/**
 * Indicates an unknown or unsupported command.
 */
public class InvalidCommandException extends InvalidInputException {
    /**
     * Creates an exception with the given message.
     *
     * @param s error message
     */
    public InvalidCommandException(String s) {
        super(s);
    }
}
