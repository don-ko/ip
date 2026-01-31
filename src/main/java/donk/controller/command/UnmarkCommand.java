package donk.controller.command;

import donk.model.exception.InvalidInputException;
import donk.model.exception.InvalidTaskNumberException;
import donk.model.exception.StorageException;
import donk.model.task.Task;
import donk.model.task.TaskList;

public class UnmarkCommand extends Command {
    private final int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public String execute(TaskList tasks) throws InvalidInputException, StorageException {
        if (idx >= tasks.size()) {
            throw new InvalidTaskNumberException("invalid task number given! there are only " + tasks.size() + " tasks.");
        }
        Task task = tasks.unmark(idx);
        return "unnice you unfinished a task.\n" + task.toString();
    }
}
