package donk.model.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import donk.model.task.Deadline;
import donk.model.task.Event;
import donk.model.task.Task;
import donk.model.task.ToDo;
import donk.testutil.StorageTestHelper;

class StorageTest {

    @TempDir
    Path tempDir;

    private StorageTestHelper storageHelper;

    @BeforeEach
    void setUp() throws IOException {
        storageHelper = new StorageTestHelper(tempDir);
        storageHelper.backup();
    }

    @AfterEach
    void tearDown() throws IOException {
        storageHelper.restore();
    }

    @Test
    void saveAndLoad_roundTripPreservesSerialisedTasks() throws Exception {
        Storage storage = new Storage();
        List<Task> original = List.of(
                new ToDo("todo"),
                new Deadline("deadline", "tomorrow"),
                new Event("event", "2pm", "4pm")
        );
        original.get(1).markDone();

        storage.save(original);
        List<Task> loaded = storage.load();

        assertEquals(original.size(), loaded.size());
        for (int i = 0; i < original.size(); i++) {
            assertEquals(original.get(i).serialiseTask(), loaded.get(i).serialiseTask());
        }
    }

    @Test
    void load_ignoresInvalidLines() throws Exception {
        Path dataPath = storageHelper.getDataPath();
        Files.createDirectories(dataPath.getParent());
        Files.write(dataPath, List.of(
                "",
                "INVALID",
                "T | 0 | valid todo",
                "D | 1 | valid deadline | 2025-01-02",
                "E | 0 | valid event | 2pm"
        ));

        Storage storage = new Storage();
        List<Task> loaded = storage.load();

        assertEquals(2, loaded.size());
        assertTrue(loaded.get(0).toString().contains("valid todo"));
        assertTrue(loaded.get(1).toString().contains("valid deadline"));
    }

    @Test
    void load_parsesDoneStatus() throws Exception {
        Path dataPath = storageHelper.getDataPath();
        Files.createDirectories(dataPath.getParent());
        Files.write(dataPath, List.of(
                "T | 1 | done todo",
                "D | 0 | date task | 2025-01-02",
                "E | 1 | done event | 2pm | 4pm"
        ));

        Storage storage = new Storage();
        List<Task> loaded = storage.load();

        assertEquals(3, loaded.size());
        assertTrue(loaded.get(0).toString().contains("[X]"));
        assertTrue(loaded.get(2).toString().contains("[X]"));
    }

    @Test
    void load_parsesDeadlineWithRawDate() throws Exception {
        Path dataPath = storageHelper.getDataPath();
        Files.createDirectories(dataPath.getParent());
        Files.write(dataPath, List.of(
                "D | 0 | submit | 2025-01-02"
        ));

        Storage storage = new Storage();
        List<Task> loaded = storage.load();

        assertEquals(1, loaded.size());
        assertTrue(loaded.get(0).toString().contains("2025-01-02"));
        assertEquals("D | 0 | submit | 2025-01-02", loaded.get(0).serialiseTask());
    }
}
