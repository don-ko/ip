package donk.controller.command;

import donk.model.exception.StorageException;
import donk.model.task.Event;
import donk.model.task.TaskList;

public class EventCommand extends Command {

    private final String taskDesc;
    private final String start;
    private final String end;

    public EventCommand(String taskDesc, String start, String end) {
        this.taskDesc = taskDesc;
        this.start = start;
        this.end = end;
    }

    @Override
    public String execute(TaskList tasks) throws StorageException {
        tasks.add(new Event(taskDesc, start, end));
        return "rip u got a new event: " + taskDesc + ".\nyou have " + tasks.size() + " tasks in the list.";
    }
}
