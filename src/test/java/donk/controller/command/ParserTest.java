package donk.controller.command;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import donk.model.exception.InvalidArgumentException;
import donk.model.exception.InvalidCommandException;
import donk.model.exception.InvalidTaskNumberException;

class ParserTest {

    private final Parser parser = new Parser();

    @Test
    void parse_exitKeyword_returnsExitCommand() throws Exception {
        Command command = parser.parse("exit");
        assertInstanceOf(ExitCommand.class, command);
    }

    @Test
    void parse_todoMissingDescription_throwsInvalidArgument() {
        assertThrows(InvalidArgumentException.class, () -> parser.parse("todo   "));
    }

    @Test
    void parse_deadlineValid_buildsDeadlineCommand() throws Exception {
        Command command = parser.parse("deadline submit /by 2025-01-02");
        assertInstanceOf(DeadlineCommand.class, command);
    }

    @Test
    void parse_deadlineMissingBy_throwsInvalidArgument() {
        assertThrows(InvalidArgumentException.class, () -> parser.parse("deadline submit"));
    }

    @Test
    void parse_eventValid_buildsEventCommand() throws Exception {
        Command command = parser.parse("event meetup /from 2pm /to 4pm");
        assertInstanceOf(EventCommand.class, command);
    }

    @Test
    void parse_eventMissingTo_throwsInvalidArgument() {
        assertThrows(InvalidArgumentException.class, () -> parser.parse("event meetup /from 2pm"));
    }

    @Test
    void parse_markNonNumeric_throwsInvalidTaskNumber() {
        assertThrows(InvalidTaskNumberException.class, () -> parser.parse("mark hello"));
    }

    @Test
    void parse_deleteZeroIndex_throwsInvalidTaskNumber() {
        assertThrows(InvalidTaskNumberException.class, () -> parser.parse("delete 0"));
    }

    @Test
    void parse_unknownKeyword_throwsInvalidCommand() {
        assertThrows(InvalidCommandException.class, () -> parser.parse("dance"));
    }
}
