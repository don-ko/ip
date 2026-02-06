package donk.controller.command;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import donk.model.exception.InvalidArgumentException;
import donk.model.exception.InvalidCommandException;
import donk.model.exception.InvalidInputException;
import donk.model.exception.InvalidTaskNumberException;

/**
 * Parses raw user input into executable command objects.
 */
public class Parser {

    /**
     * Parses the given input into a {@link Command}.
     *
     * @param rawInput user input line
     * @return command instance representing the input
     * @throws InvalidInputException if the input is invalid or incomplete
     */
    public Command parse(String rawInput) throws InvalidInputException {
        String input = rawInput == null ? "" : rawInput.trim();

        String[] parts = input.split(" ", 2);
        String keyword = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1] : "";

        Command command;
        switch (keyword) {
        case "exit":
        // Fallthrough
        case "bye":
            command = new ExitCommand();
            break;

        case "list":
            command = new ListCommand();
            break;

        case "mark":
            command = new MarkCommand(parseTaskNumber(args));
            break;

        case "unmark":
            command = new UnmarkCommand(parseTaskNumber(args));
            break;

        case "delete":
            command = new DeleteCommand(parseTaskNumber(args));
            break;

        case "find":
            if (args.isBlank()) {
                throw new InvalidArgumentException("what are you finding?");
            }
            command = new FindCommand(args);
            break;

        case "todo":
            if (args.isBlank()) {
                throw new InvalidArgumentException("invalid todo! ure doing nothing :(");
            }
            command = new TodoCommand(args);
            break;

        case "deadline":
            String[] deadlineArgs = parseDeadline(args);
            if (Arrays.stream(deadlineArgs).anyMatch(String::isEmpty)) {
                throw new InvalidArgumentException("invalid deadline! ure doing nothing :(");
            }
            command = new DeadlineCommand(deadlineArgs[0], deadlineArgs[1]);
            break;

        case "event":
            String[] eventArgs = parseEvent(args);
            if (Arrays.stream(eventArgs).anyMatch(String::isEmpty)) {
                throw new InvalidArgumentException("invalid todo! ure doing nothing :(");
            }
            command = new EventCommand(eventArgs[0], eventArgs[1], eventArgs[2]);
            break;

        default:
            throw new InvalidCommandException("invalid command given! try: mark, unmark, todo");
        }
        return command;
    }

    /**
     * Extracts a deadline description and by-date from the args string.
     *
     * @param args deadline arguments string
     * @return array containing description and by-date
     * @throws InvalidArgumentException if the args are malformed
     */
    private String[] parseDeadline(String args) throws InvalidArgumentException {
        Pattern deadlinePattern = Pattern.compile("^(.+?)(?= /)");
        Pattern byPattern = Pattern.compile("(?<= /by )(.+?)(?= /|$)");

        Matcher deadlineMatcher = deadlinePattern.matcher(args);
        Matcher byMatcher = byPattern.matcher(args);
        if (deadlineMatcher.find() && byMatcher.find()) {
            return new String[]{deadlineMatcher.group(1), byMatcher.group(1)};
        } else {
            throw new InvalidArgumentException("invalid deadline!");
        }
    }

    /**
     * Extracts event description, start, and end values from the args string.
     *
     * @param args event arguments string
     * @return array containing description, start, and end
     * @throws InvalidArgumentException if the args are malformed
     */
    private String[] parseEvent(String args) throws InvalidArgumentException {
        Pattern eventPattern = Pattern.compile("^(.+?)(?= /)");
        Pattern fromPattern = Pattern.compile("(?<= /from )(.+?)(?= /|$)");
        Pattern toPattern = Pattern.compile("(?<= /to )(.+?)(?= /|$)");

        Matcher eventMatcher = eventPattern.matcher(args);
        Matcher fromMatcher = fromPattern.matcher(args);
        Matcher toMatcher = toPattern.matcher(args);

        if (eventMatcher.find() && fromMatcher.find() && toMatcher.find()) {
            return new String[]{eventMatcher.group(), fromMatcher.group(), toMatcher.group()};
        } else {
            throw new InvalidArgumentException("invalid event times!");
        }
    }

    /**
     * Parses the 1-based task index from user input.
     *
     * @param idx raw index string
     * @return zero-based task index
     * @throws InvalidTaskNumberException if the index is invalid
     */
    private int parseTaskNumber(String idx) throws InvalidTaskNumberException {
        int n;
        try {
            n = Integer.parseInt(idx) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException("invalid input! a valid task number needs to be given.");
        }
        if (n < 0) {
            throw new InvalidTaskNumberException("invalid task number! task number needs to be >0.");
        }
        return n;
    }
}
