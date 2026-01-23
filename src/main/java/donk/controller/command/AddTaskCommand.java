package donk.controller.command;

import donk.model.task.Task;
import donk.model.task.TaskList;

public class AddTaskCommand extends Command {
    private final String taskDesc;

    public AddTaskCommand(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    @Override
    public String execute(TaskList tasks) {
        tasks.add(new Task(taskDesc));
        return "added: " + taskDesc + "\n";
    }
}
