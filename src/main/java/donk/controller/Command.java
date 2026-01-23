package donk.controller;

import donk.model.task.TaskList;

public abstract class Command {
    public abstract String execute(TaskList tasks);
    public boolean isExit() { return false; }
}
