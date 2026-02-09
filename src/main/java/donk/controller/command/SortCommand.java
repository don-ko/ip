package donk.controller.command;

import donk.model.exception.StorageException;
import donk.model.task.TaskList;

/**
 * Sorts all tasks in the task list.
 */
public class SortCommand extends Command {
    /**
     * Returns the string representation of the sorted task list.
     *
     * @param tasks target task list.
     * @return formatted sorted list of tasks.
     */
    public String execute(TaskList tasks) throws StorageException {
        tasks.sortTasks();
        return tasks.toString();
    }
}
