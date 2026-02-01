package donk.controller.command;

import donk.model.exception.InvalidInputException;
import donk.model.exception.InvalidTaskNumberException;
import donk.model.exception.StorageException;
import donk.model.task.Task;
import donk.model.task.TaskList;

/**
 * Marks a task as not completed.
 */
public class UnmarkCommand extends Command {
    private final int idx;

    /**
     * Creates an unmark command for a task index.
     *
     * @param idx zero-based task index
     */
    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Unmarks the task and returns a confirmation message.
     *
     * @param tasks target task list
     * @return confirmation message
     * @throws InvalidInputException if the index is out of range
     * @throws StorageException if saving fails
     */
    @Override
    public String execute(TaskList tasks) throws InvalidInputException, StorageException {
        if (idx >= tasks.size()) {
            throw new InvalidTaskNumberException("invalid task number given! there are only " + tasks.size() + " tasks.");
        }
        Task task = tasks.unmark(idx);
        return "unnice you unfinished a task.\n" + task.toString();
    }
}
