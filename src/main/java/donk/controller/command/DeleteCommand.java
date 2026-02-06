package donk.controller.command;

import donk.model.exception.InvalidInputException;
import donk.model.exception.InvalidTaskNumberException;
import donk.model.exception.StorageException;
import donk.model.task.Task;
import donk.model.task.TaskList;

/**
 * Deletes a task at a given index.
 */
public class DeleteCommand extends Command {
    private final int idx;

    /**
     * Creates a delete command for a task index.
     *
     * @param idx zero-based task index
     */
    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Removes the task and returns a confirmation message.
     *
     * @param tasks target task list
     * @return confirmation message
     * @throws InvalidInputException if the index is out of range
     * @throws StorageException if saving fails
     */
    @Override
    public String execute(TaskList tasks) throws InvalidInputException, StorageException {
        if (idx >= tasks.size()) {
            throw new InvalidTaskNumberException("invalid task number given! there are only "
                    + tasks.size() + " tasks.");
        }
        Task task = tasks.delete(idx);
        return ":0 you deleted a task.\n" + task.toString();
    }
}
