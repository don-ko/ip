package donk.controller.command;

import donk.model.task.TaskList;

/**
 * Lists all tasks currently in the task list.
 */
public class ListCommand extends Command {
    /**
     * Returns the string representation of the task list.
     *
     * @param tasks target task list.
     * @return formatted list of tasks.
     */
    @Override
    public String execute(TaskList tasks) {
        return tasks.toString();
    }
}
