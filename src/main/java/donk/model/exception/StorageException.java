package donk.model.exception;

/**
 * Indicates a failure reading or writing storage.
 */
public class StorageException extends Exception {
    /**
     * Creates an exception with the given message.
     *
     * @param message error message
     */
    public StorageException(String message) {
        super(message);
    }
}
