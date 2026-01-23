package donk.controller.command;

import donk.model.exceptions.InvalidInputException;
import donk.model.exceptions.InvalidTaskNumberException;
import donk.model.task.Task;
import donk.model.task.TaskList;

public class UnmarkCommand extends Command {
    private final int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public String execute(TaskList tasks) throws InvalidInputException {
        if (idx >= tasks.size()) {
            throw new InvalidTaskNumberException("Invalid task number given! There are only " + tasks.size() + " tasks.");
        }
        Task task = tasks.unmark(idx);
        return "Unnice you unfinished a task.\n" + task.toString() + "\n";
    }
}
