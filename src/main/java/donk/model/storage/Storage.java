package donk.model.storage;

import donk.model.task.Deadline;
import donk.model.task.Event;
import donk.model.task.Task;
import donk.model.task.ToDo;

import java.io.BufferedWriter;
import java.io.IOException;
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
    private static final String DONE_MARKER = "1";
    
    private static final String TYPE_TODO = "T";
    private static final String TYPE_DEADLINE = "D";
    private static final String TYPE_EVENT = "E";

    private static final int NUM_FIELDS_MIN = 3;
    private static final int NUM_FIELDS_DEADLINE = 4; // TYPE | DONE_STATUS | DESCRIPTION | BY
    private static final int NUM_FIELDS_EVENT = 5;    // TYPE | DONE_STATUS | DESCRIPTION | START | END

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
     */
    private void createStorageFile() throws IOException {
        Files.createDirectories(DATA_PATH.getParent());
        Files.createFile(DATA_PATH);
    }

    /**
     * Parses a line from storage into a Task object.
     */
    private Optional<Task> parseTask(String line) {
        if (line == null || line.trim().isEmpty()) {
            return Optional.empty();
        }

        String[] parts = line.split("\\s*\\|\\s*");
        
        if (parts.length < NUM_FIELDS_MIN) {
            return Optional.empty();
        }

        String type = parts[0].trim();
        boolean isDone = DONE_MARKER.equals(parts[1].trim());
        String description = parts[2].trim();

        return switch (type) {
            case TYPE_TODO -> createToDo(description, isDone);
            case TYPE_DEADLINE -> createDeadline(parts, description, isDone);
            case TYPE_EVENT -> createEvent(parts, description, isDone);
            default -> Optional.empty();
        };
    }

    private Optional<Task> createToDo(String description, boolean isDone) {
        ToDo todo = new ToDo(description);
        if (isDone) {
            todo.markDone();
        }
        return Optional.of(todo);
    }

    private Optional<Task> createDeadline(String[] parts, String description, boolean isDone) {
        if (parts.length >= NUM_FIELDS_DEADLINE) {
            String by = parts[3].trim();
            Deadline deadline = new Deadline(description, by);
            if (isDone) {
                deadline.markDone();
            }
            return Optional.of(deadline);
        }
        return Optional.empty();
    }

    private Optional<Task> createEvent(String[] parts, String description, boolean isDone) {
        if (parts.length >= NUM_FIELDS_EVENT) {
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