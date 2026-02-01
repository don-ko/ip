package donk.model.task;

/**
 * Base type for tasks with a description and completion state.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * Creates a task with the given description.
     *
     * @param description task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon used in UI output.
     *
     * @return "X" if done, otherwise a blank
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as completed.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmarkDone() {
        isDone = false;
    }

    /**
     * Serializes this task for storage.
     *
     * @return serialized task string
     */
    public String serialiseTask() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a human-readable representation of the task.
     *
     * @return formatted task string
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
