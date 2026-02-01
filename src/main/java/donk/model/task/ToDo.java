package donk.model.task;

public class ToDo extends Task {
<<<<<<< Updated upstream

=======
    /**
     * Creates a todo task with the given description.
     *
     * @param description todo description
     */
>>>>>>> Stashed changes
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String serialiseTask() {
        return "T | " + super.serialiseTask();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
