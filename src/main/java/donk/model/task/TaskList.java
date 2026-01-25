package donk.model.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks = new ArrayList<>();

    public void add(Task task) {
        tasks.add(task);
    }

    public Task mark(int index) {
        Task task = tasks.get(index);
        task.markDone();
        return task;
    }

    public Task unmark(int index) {
        Task task = tasks.get(index);
        task.unmarkDone();
        return task;
    }

    public int size() {
        return tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            sb.append(i).append(".").append(tasks.get(i - 1));
            if (i < tasks.size()) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
