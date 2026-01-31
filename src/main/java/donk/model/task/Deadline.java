package donk.model.task;

public class Deadline extends Task {

    private final String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String serialiseTask() {
        return "D | " + super.serialiseTask() + " | " + deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
