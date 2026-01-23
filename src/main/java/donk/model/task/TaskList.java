package donk.model.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks = new ArrayList<>();

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public Task remove(int index) {
        return tasks.remove(index);
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
