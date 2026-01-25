package donk.controller.command;

import donk.model.task.Deadline;
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
    public String execute(TaskList tasks) {
        tasks.add(new Event(this.taskDesc, this.start, this.end));
        return "rip u got a new event: " + this.taskDesc + ".\nyou have " + tasks.size() + " tasks in the list.";
    }
}
