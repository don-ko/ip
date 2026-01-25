package donk.controller.command;

import donk.model.exceptions.InvalidArgumentException;
import donk.model.exceptions.InvalidCommandException;
import donk.model.exceptions.InvalidInputException;
import donk.model.exceptions.InvalidTaskNumberException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public Command parse(String rawInput) throws InvalidInputException {
        String input = rawInput == null ? "" : rawInput.trim();

        String[] parts = input.split(" ", 2);
        String keyword = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1] : "";

        switch (keyword) {
        case "exit":
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "mark":
            return new MarkCommand(parseTaskNumber(args));

        case "unmark":
            return new UnmarkCommand(parseTaskNumber(args));

        case "todo":
            return new TodoCommand(args);

        case "deadline":
            String[] deadlineArgs = parseDeadline(args);
            return new DeadlineCommand(deadlineArgs[0], deadlineArgs[1]);

        case "event":
            String[] eventArgs = parseEvent(args);
            return new EventCommand(eventArgs[0], eventArgs[1], eventArgs[2]);

        default:
            throw new InvalidCommandException("invalid command given! try: mark, unmark, todo");
        }
    }

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

    private String[] parseEvent(String args) throws InvalidArgumentException {
        Pattern eventPattern = Pattern.compile("^(.+?)(?= /)");
        Pattern fromPattern = Pattern.compile("(?<= /from )(.+?)(?= /|$)");
        Pattern toPattern = Pattern.compile("(?<= /to )(.+?)(?= /|$)");

        Matcher eventMatcher = eventPattern.matcher(args);
        Matcher fromMatcher = fromPattern.matcher(args);
        Matcher toMatcher = toPattern.matcher(args);

        if (eventMatcher.find() && fromMatcher.find() && toMatcher.find()) {
            return new String[] { eventMatcher.group(), fromMatcher.group(), toMatcher.group() };
        } else {
            throw new InvalidArgumentException("invalid event times!");
        }
    }

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
