package donk.model.task;

import java.io.IOException;
import java.util.List;

import donk.model.exception.StorageException;
import donk.model.storage.Storage;

/**
 * Wraps task collection behavior and persistence interactions.
 */
public class TaskList {
    private final List<Task> tasks;
    private final Storage storage;

    /**
     * Loads tasks from storage or starts with an empty list if loading fails.
     */
    public TaskList() {
        List<Task> temp;
        storage = new Storage();
        try {
            temp = storage.load();
        } catch (IOException e) {
            temp = new java.util.ArrayList<>();
        }
        tasks = temp;
    }

    /**
     * Persists current tasks to storage.
     *
     * @throws StorageException if saving fails.
     */
    private void save() throws StorageException {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new StorageException("unable to save. something wrong with storage >:(");
        }
    }

    /**
     * Adds a task and persists the list.
     *
     * @param task task to add.
     * @throws StorageException if saving fails.
     */
    public void add(Task task) throws StorageException {
        tasks.add(task);
        save();
    }

    /**
     * Deletes a task at the given index and persists the list.
     *
     * @param index zero-based task index.
     * @return removed task.
     * @throws StorageException if saving fails.
     */
    public Task delete(int index) throws StorageException {
        Task task = tasks.remove(index);
        save();
        return task;
    }

    /**
     * Marks the task at the given index and persists the list.
     *
     * @param index zero-based task index.
     * @return updated task.
     * @throws StorageException if saving fails.
     */
    public Task mark(int index) throws StorageException {
        Task task = tasks.get(index);
        task.markDone();
        save();
        return task;
    }

    /**
     * Unmarks the task at the given index and persists the list.
     *
     * @param index zero-based task index.
     * @return updated task.
     * @throws StorageException if saving fails.
     */
    public Task unmark(int index) throws StorageException {
        Task task = tasks.get(index);
        task.unmarkDone();
        save();
        return task;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return task count.
     */
    public Task[] searchTasks(String searchString) {
        return tasks.stream()
                .filter(task -> task.toString().toLowerCase().contains(searchString))
                .toArray(Task[]::new);
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Returns a formatted list of tasks for display.
     *
     * @return formatted task list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            sb.append(i).append(".").append(tasks.get(i - 1)).append("\n");
        }
        return sb.toString().trim();
    }
}
