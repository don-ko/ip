package donk.controller.command;

import donk.model.exceptions.InvalidInputException;
import donk.model.exceptions.InvalidTaskNumberException;
import donk.model.exceptions.StorageException;
import donk.model.task.Task;
import donk.model.task.TaskList;

public class DeleteCommand extends Command {
    private final int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public String execute(TaskList tasks) throws InvalidInputException, StorageException {
        if (idx >= tasks.size()) {
            throw new InvalidTaskNumberException("invalid task number given! there are only " + tasks.size() + " tasks.");
        }
        Task task = tasks.delete(idx);
        return ":0 you deleted a task.\n" + task.toString();
    }
}
