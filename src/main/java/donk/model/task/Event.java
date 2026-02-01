package donk.model.task;

/**
 * Represents a task with a start and end time range.
 */
public class Event extends Task {
    private final String start;
    private final String end;

    /**
     * Creates an event task with a start and end time.
     *
     * @param description task description
     * @param start event start time text
     * @param end event end time text
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Serializes this event task for storage.
     *
     * @return serialized task string
     */
    @Override
    public String serialiseTask() {
        return "E | " + super.serialiseTask() + " | " + start + " | " + end;
    }

    /**
     * Returns a human-readable representation of the event task.
     *
     * @return formatted event string
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
