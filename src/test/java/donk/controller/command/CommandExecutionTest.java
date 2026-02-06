package donk.controller.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import donk.model.exception.InvalidTaskNumberException;
import donk.model.exception.StorageException;
import donk.model.task.TaskList;
import donk.model.task.ToDo;
import donk.testutil.StorageTestHelper;

class CommandExecutionTest {

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
    void todoCommand_addsTaskAndReturnsMessage() throws Exception {
        TaskList tasks = new TaskList();
        String message = new TodoCommand("read book").execute(tasks);

        assertEquals(1, tasks.size());
        assertTrue(message.contains("rip u got a new todo"));
    }

    @Test
    void deadlineCommand_validDateFormatsDeadline() throws Exception {
        TaskList tasks = new TaskList();
        String message = new DeadlineCommand("submit", "2025-01-02").execute(tasks);

        assertEquals(1, tasks.size());
        assertTrue(message.contains("rip u got a new deadline"));
        assertTrue(tasks.toString().contains("Jan 2 2025"));
    }

    @Test
    void deadlineCommand_invalidDateKeepsRawString() throws Exception {
        TaskList tasks = new TaskList();
        String message = new DeadlineCommand("submit", "2025/01/02").execute(tasks);

        assertEquals(1, tasks.size());
        assertTrue(message.contains("date format is invalid"));
        assertTrue(tasks.toString().contains("2025/01/02"));
    }

    @Test
    void eventCommand_addsEvent() throws Exception {
        TaskList tasks = new TaskList();
        String message = new EventCommand("meetup", "2pm", "4pm").execute(tasks);

        assertEquals(1, tasks.size());
        assertTrue(message.contains("rip u got a new event"));
    }

    @Test
    void markCommand_outOfRange_throwsInvalidTaskNumber() {
        TaskList tasks = new TaskList();
        assertThrows(InvalidTaskNumberException.class, () -> new MarkCommand(1).execute(tasks));
    }

    @Test
    void unmarkCommand_outOfRange_throwsInvalidTaskNumber() {
        TaskList tasks = new TaskList();
        assertThrows(InvalidTaskNumberException.class, () -> new UnmarkCommand(1).execute(tasks));
    }

    @Test
    void deleteCommand_outOfRange_throwsInvalidTaskNumber() {
        TaskList tasks = new TaskList();
        assertThrows(InvalidTaskNumberException.class, () -> new DeleteCommand(1).execute(tasks));
    }

    @Test
    void listCommand_returnsTaskListString() throws StorageException {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("task a"));

        String output = new ListCommand().execute(tasks);

        assertEquals(tasks.toString(), output);
    }

    @Test
    void exitCommand_returnsAsciiByeAndIsExit() {
        ExitCommand command = new ExitCommand();

        String output = command.execute(new TaskList());

        assertTrue(command.isExit());
        assertTrue(output.contains("Bye!!!"));
    }

    @Test
    void findCommand_matchesTasksCaseInsensitiveAndTrimmed() throws Exception {
        TaskList tasks = new TaskList();
        ToDo first = new ToDo("Read Book");
        ToDo second = new ToDo("buy milk");
        ToDo third = new ToDo("read notes");
        tasks.add(first);
        tasks.add(second);
        tasks.add(third);

        String output = new FindCommand("  READ  ").execute(tasks);

        String expected = "found matching tasks!\n"
                + "1." + first + "\n"
                + "2." + third;
        assertEquals(expected, output);
    }

    @Test
    void findCommand_noMatches_returnsNoMatchMessage() throws Exception {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("alpha"));

        String output = new FindCommand("zzz").execute(tasks);

        assertEquals("no matching tasks found :(", output);
    }
}
