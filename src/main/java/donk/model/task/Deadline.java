package donk.model.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final String deadline;
    private final LocalDate deadlineDate;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        deadlineDate = null;
    }

    public Deadline(String description, LocalDate deadlineDate) {
        super(description);
        deadline = "";
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String serialiseTask() {
        return "D | " + super.serialiseTask() + " | " + deadline;
    }

    @Override
    public String toString() {
        String formattedDeadline = (deadlineDate != null)
                ? deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                : deadline;
        return "[D]" + super.toString() + " (by: " + formattedDeadline + ")";
    }
}
