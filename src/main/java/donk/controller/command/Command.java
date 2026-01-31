package donk.controller.command;

import donk.model.exceptions.InvalidInputException;
import donk.model.exceptions.StorageException;
import donk.model.task.TaskList;

public abstract class Command {
    public abstract String execute(TaskList tasks) throws InvalidInputException, StorageException;
    public boolean isExit() { return false; }
}
