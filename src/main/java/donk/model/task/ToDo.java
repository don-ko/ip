package donk.model.task;

/**
 * Represents a todo task.
 */
public class ToDo extends Task {

    /**
     * Creates a todo task with the given description.
     *
     * @param description todo description
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Serializes this todo task for storage.
     *
     * @return serialized task string
     */
    @Override
    public String serialiseTask() {
        return "T | " + super.serialiseTask();
    }

    /**
     * Returns a human-readable representation of the todo task.
     *
     * @return formatted todo string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
