package donk.controller.command;

import donk.model.exception.StorageException;
import donk.model.task.TaskList;
import donk.model.task.ToDo;

/**
 * Adds a todo task to the task list.
 */
public class TodoCommand extends Command {
    private final String taskDesc;

    /**
     * Creates a command for a todo description.
     *
     * @param taskDesc todo description
     */
    public TodoCommand(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    /**
     * Adds a todo task to the list and returns a confirmation message.
     *
     * @param tasks target task list
     * @return confirmation message
     * @throws StorageException if saving fails
     */
    @Override
    public String execute(TaskList tasks) throws StorageException {
        tasks.add(new ToDo(taskDesc));
        return "rip u got a new todo: " + taskDesc + ".\nyou have " + tasks.size() + " tasks in the list.";
    }
}
