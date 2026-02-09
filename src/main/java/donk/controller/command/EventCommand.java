package donk.controller.command;

import donk.model.exception.StorageException;
import donk.model.task.Event;
import donk.model.task.TaskList;

/**
 * Adds an event task to the task list.
 */
public class EventCommand extends Command {
    private final String taskDesc;
    private final String start;
    private final String end;

    /**
     * Creates a command for an event description and time range.
     *
     * @param taskDesc event description.
     * @param start event start time text.
     * @param end event end time text.
     */
    public EventCommand(String taskDesc, String start, String end) {
        this.taskDesc = taskDesc;
        this.start = start;
        this.end = end;
    }

    /**
     * Adds an event task to the list and returns a confirmation message.
     *
     * @param tasks target task list.
     * @return confirmation message.
     * @throws StorageException if saving fails.
     */
    @Override
    public String execute(TaskList tasks) throws StorageException {
        tasks.add(new Event(taskDesc, start, end));
        return "rip u got a new event: " + taskDesc + ".\nyou have " + tasks.size() + " tasks in the list.";
    }
}
