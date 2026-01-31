package donk.controller.command;

import donk.model.exception.StorageException;
import donk.model.task.TaskList;
import donk.model.task.ToDo;

public class TodoCommand extends Command {
    private final String taskDesc;

    public TodoCommand(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    @Override
    public String execute(TaskList tasks) throws StorageException {
        tasks.add(new ToDo(taskDesc));
        return "rip u got a new todo: " + taskDesc + ".\nyou have " + tasks.size() + " tasks in the list.";
    }
}
