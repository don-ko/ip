package donk.controller.command;

import donk.model.exception.InvalidInputException;
import donk.model.exception.StorageException;
import donk.model.task.TaskList;

public abstract class Command {
    public abstract String execute(TaskList tasks) throws InvalidInputException, StorageException;
    public boolean isExit() { return false; }
}
