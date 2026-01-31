package donk.model.task;

import donk.model.exception.StorageException;
import donk.model.storage.Storage;

import java.io.IOException;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;
    private final Storage storage;

    public TaskList() {
        List<Task> temp;
        this.storage = new Storage();
        try {
            temp = storage.load();
        } catch (IOException e) {
            temp = new java.util.ArrayList<>();
        }
        this.tasks = temp;
    }

    private void save() throws StorageException {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new StorageException("unable to save. something wrong with storage >:(");
        }
    }

    public void add(Task task) throws StorageException {
        tasks.add(task);
        save();
    }

    public Task delete(int index) throws StorageException {
        Task task = tasks.remove(index);
        save();
        return task;
    }

    public Task mark(int index) throws StorageException {
        Task task = tasks.get(index);
        task.markDone();
        save();
        return task;
    }

    public Task unmark(int index) throws StorageException {
        Task task = tasks.get(index);
        task.unmarkDone();
        save();
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
