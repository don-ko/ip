package donk.model.storage;

import donk.model.task.Deadline;
import donk.model.task.Event;
import donk.model.task.Task;
import donk.model.task.ToDo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Handles the storage and retrieval of tasks from a file.
 * Tasks are stored in a pipe-separated format in the data/donk.txt file.
 * Format: TYPE | DONE_STATUS | DESCRIPTION | [ADDITIONAL_FIELDS]
 */
public class Storage {
    private static final Path DATA_PATH = Paths.get("data", "donk.txt");
    private static final int MIN_TASK_FIELDS = 3;
    private static final String DONE_MARKER = "1";
    private static final String TODO_TYPE = "T";
    private static final String DEADLINE_TYPE = "D";
    private static final String EVENT_TYPE = "E";
    private static final int DEADLINE_FIELDS = 4; // TYPE | DONE_STATUS | DESCRIPTION | BY
    private static final int EVENT_FIELDS = 5;    // TYPE | DONE_STATUS | DESCRIPTION | START | END

    /**
     * Loads all tasks from the storage file.
     * Creates the file and directories if they don't exist.
     * 
     * @return List of tasks loaded from storage
     * @throws IOException if there's an error reading the file
     */
    public List<Task> load() throws IOException {
        if (Files.notExists(DATA_PATH)) {
            createStorageFile();
            return new ArrayList<>();
        }

        List<Task> tasks = new ArrayList<>();
        List<String> lines = Files.readAllLines(DATA_PATH);
        
        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                parseTask(line).ifPresent(tasks::add);
            }
        }
        
        return tasks;
    }

    /**
     * Saves all tasks to the storage file.
     * 
     * @param tasks List of tasks to save
     * @throws IOException if there's an error writing to the file
     */
    public void save(List<Task> tasks) throws IOException {
        Files.createDirectories(DATA_PATH.getParent());
        
        try (BufferedWriter writer = Files.newBufferedWriter(DATA_PATH)) {
            for (Task task : tasks) {
                writer.write(task.serialiseTask());
                writer.newLine();
            }
        }
    }

    /**
     * Creates the storage file and its parent directories.
     *
     * @throws IOException if the file cannot be created
     */
    private void createStorageFile() throws IOException {
        Files.createDirectories(DATA_PATH.getParent());
        Files.createFile(DATA_PATH);
    }

    /**
     * Parses a line from storage into a Task object.
     *
     * @param line raw storage line
     * @return parsed task if valid, otherwise empty
     */
    private Optional<Task> parseTask(String line) {
        if (line == null || line.trim().isEmpty()) {
            return Optional.empty();
        }

        String[] parts = line.split("\\s*\\|\\s*");
        
        if (parts.length < MIN_TASK_FIELDS) {
            return Optional.empty();
        }

        String type = parts[0].trim();
        boolean isDone = DONE_MARKER.equals(parts[1].trim());
        String description = parts[2].trim();

        return switch (type) {
            case TODO_TYPE -> createToDo(description, isDone);
            case DEADLINE_TYPE -> createDeadline(parts, description, isDone);
            case EVENT_TYPE -> createEvent(parts, description, isDone);
            default -> Optional.empty();
        };
    }

    /**
     * Builds a todo task from storage fields.
     *
     * @param description task description
     * @param isDone completion flag
     * @return parsed todo task
     */
    private Optional<Task> createToDo(String description, boolean isDone) {
        ToDo todo = new ToDo(description);
        if (isDone) {
            todo.markDone();
        }
        return Optional.of(todo);
    }

    /**
     * Builds a deadline task from storage fields.
     *
     * @param parts storage fields
     * @param description task description
     * @param isDone completion flag
     * @return parsed deadline task if fields are valid
     */
    private Optional<Task> createDeadline(String[] parts, String description, boolean isDone) {
        if (parts.length >= DEADLINE_FIELDS) {
            String by = parts[3].trim();
            Deadline deadline = new Deadline(description, by);
            if (isDone) {
                deadline.markDone();
            }
            return Optional.of(deadline);
        }
        return Optional.empty();
    }

    /**
     * Builds an event task from storage fields.
     *
     * @param parts storage fields
     * @param description task description
     * @param isDone completion flag
     * @return parsed event task if fields are valid
     */
    private Optional<Task> createEvent(String[] parts, String description, boolean isDone) {
        if (parts.length >= EVENT_FIELDS) {
            String start = parts[3].trim();
            String end = parts[4].trim();
            Event event = new Event(description, start, end);
            if (isDone) {
                event.markDone();
            }
            return Optional.of(event);
        }
        return Optional.empty();
    }
}