package donk.controller.command;

import donk.model.exceptions.StorageException;
import donk.model.task.Deadline;
import donk.model.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {

    private final String taskDesc;
    private final String deadline;

    public DeadlineCommand(String taskDesc, String deadline) {
        this.taskDesc = taskDesc;
        this.deadline = deadline;
    }

    @Override
    public String execute(TaskList tasks) throws StorageException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateTime = LocalDate.parse(deadline, formatter);
            tasks.add(new Deadline(taskDesc, dateTime));

            return "rip u got a new deadline: " + taskDesc + ".\nyou have " + tasks.size() + " tasks in the list.";
        } catch (DateTimeParseException e) {
            tasks.add(new Deadline(taskDesc, deadline));

            return "date format is invalid, deadline added without date parsing :(\n" +
                    "rip u got a new deadline: " + taskDesc + ".\nyou have " + tasks.size() + " tasks in the list.";
        }
    }
}
