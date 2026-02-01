package donk.controller.command;

import donk.model.exception.InvalidInputException;
import donk.model.exception.StorageException;
import donk.model.task.TaskList;

/**
 * Base type for user commands that act on the task list.
 */
public abstract class Command {
    /**
     * Executes the command against the given task list.
     *
     * @param tasks task list to operate on
     * @return user-facing result message
     * @throws InvalidInputException if input validation fails during execution
     * @throws StorageException if persistence fails while executing
     */
    public abstract String execute(TaskList tasks) throws InvalidInputException, StorageException;

    /**
     * Indicates whether this command should terminate the UI loop.
     *
     * @return true if this command exits the app
     */
    public boolean isExit() { return false; }
}
