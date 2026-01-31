package donk.model.task;

public class ToDo extends Task {

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
