package donk.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import donk.model.exception.StorageException;
import donk.testutil.StorageTestHelper;

class TaskListTest {

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
    void addAndToString_numbersTasks() throws Exception {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("task a"));
        tasks.add(new Event("task b", "2pm", "3pm"));

        assertEquals(2, tasks.size());
        assertTrue(tasks.toString().startsWith("1.[T]"));
        assertTrue(tasks.toString().contains("2.[E]"));
    }

    @Test
    void markAndUnmark_toggleStatus() throws Exception {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("task a"));

        Task marked = tasks.mark(0);
        assertEquals("[T][X] task a", marked.toString());

        Task unmarked = tasks.unmark(0);
        assertEquals("[T][ ] task a", unmarked.toString());
    }

    @Test
    void delete_removesAndReturnsTask() throws StorageException {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("task a"));
        tasks.add(new ToDo("task b"));

        Task removed = tasks.delete(0);

        assertEquals("[T][ ] task a", removed.toString());
        assertEquals(1, tasks.size());
        assertTrue(tasks.toString().contains("task b"));
    }
}
