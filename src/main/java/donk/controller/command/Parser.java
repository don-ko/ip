package donk.controller.command;

import donk.model.exceptions.InvalidInputException;
import donk.model.exceptions.InvalidTaskNumberException;

public class Parser {
    public Command parse(String rawInput) throws InvalidInputException {
        String input = rawInput == null ? "" : rawInput.trim();

        String[] parts = input.split(" ", 2);
        String keyword = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1] : "";

        switch (keyword) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "mark":
            return new MarkCommand(parseTaskNumber(args));

        case "unmark":
            return new UnmarkCommand(parseTaskNumber(args));

        default:
            return new AddTaskCommand(input);
        }
    }

    private int parseTaskNumber(String idx) throws InvalidTaskNumberException {
        int n;
        try {
            n = Integer.parseInt(idx) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException("Invalid input! A valid task number needs to be given.");
        }
        if (n < 0) {
            throw new InvalidTaskNumberException("Invalid task number given! Task number needs to be >0.");
        }
        return n;
    }
}
