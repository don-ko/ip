package donk.controller.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import donk.model.exception.StorageException;
import donk.model.task.Deadline;
import donk.model.task.TaskList;

/**
 * Adds a deadline task to the task list.
 */
public class DeadlineCommand extends Command {
    private final String taskDesc;
    private final String deadline;

    /**
     * Creates a command for a deadline description and date text.
     *
     * @param taskDesc deadline description
     * @param deadline deadline date string
     */
    public DeadlineCommand(String taskDesc, String deadline) {
        this.taskDesc = taskDesc;
        this.deadline = deadline;
    }

    /**
     * Adds a deadline task to the list and returns a confirmation message.
     *
     * @param tasks target task list
     * @return confirmation message
     * @throws StorageException if saving fails
     */
    @Override
    public String execute(TaskList tasks) throws StorageException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateTime = LocalDate.parse(deadline, formatter);
            tasks.add(new Deadline(taskDesc, dateTime));

            return "rip u got a new deadline: " + taskDesc + ".\nyou have " + tasks.size() + " tasks in the list.";
        } catch (DateTimeParseException e) {
            tasks.add(new Deadline(taskDesc, deadline));

            return "date format is invalid, deadline added without date parsing :(\n"
                    + "rip u got a new deadline: " + taskDesc + ".\nyou have " + tasks.size() + " tasks in the list.";
        }
    }
}
