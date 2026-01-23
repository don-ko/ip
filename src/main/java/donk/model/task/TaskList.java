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

    public List<Task> all() {
        return tasks;
    }

    @Override
    public String toString() {
        int i = 1;
        StringBuilder sb = new StringBuilder();
        for (Task t : tasks) {
            sb.append(i++ + "." + t.toString() + "\n");
        }
        return sb.toString();
    }
}
