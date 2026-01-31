package donk.controller.command;

import donk.model.exceptions.StorageException;
import donk.model.task.Deadline;
import donk.model.task.TaskList;

public class DeadlineCommand extends Command {

    private final String taskDesc;
    private final String deadline;

    public DeadlineCommand(String taskDesc, String deadline) {
        this.taskDesc = taskDesc;
        this.deadline = deadline;
    }

    @Override
    public String execute(TaskList tasks) throws StorageException {
        tasks.add(new Deadline(this.taskDesc, this.deadline));
        return "rip u got a new deadline: " + this.taskDesc + ".\nyou have " + tasks.size() + " tasks in the list.";
    }
}
