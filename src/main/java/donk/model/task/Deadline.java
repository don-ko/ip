package donk.model.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    private final String deadline;
    private final LocalDate deadlineDate;

    /**
     * Creates a deadline task with an unparsed deadline string.
     *
     * @param description task description
     * @param deadline deadline text
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        deadlineDate = null;
    }

    /**
     * Creates a deadline task with a parsed date.
     *
     * @param description task description
     * @param deadlineDate deadline date
     */
    public Deadline(String description, LocalDate deadlineDate) {
        super(description);
        deadline = "";
        this.deadlineDate = deadlineDate;
    }

    /**
     * Serializes this deadline task for storage.
     *
     * @return serialized task string
     */
    @Override
    public String serialiseTask() {
        return "D | " + super.serialiseTask() + " | " + deadline;
    }

    /**
     * Returns a human-readable representation of the deadline task.
     *
     * @return formatted deadline string
     */
    @Override
    public String toString() {
        String formattedDeadline = (deadlineDate != null)
                ? deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                : deadline;
        return "[D]" + super.toString() + " (by: " + formattedDeadline + ")";
    }
}
