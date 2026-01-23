package donk.model.command;

import donk.controller.Command;
import donk.model.task.TaskList;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks) {
        return tasks.toString();
    }
}
