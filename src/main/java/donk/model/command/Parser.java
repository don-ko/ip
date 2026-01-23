package donk.model.command;

import donk.controller.Command;

public class Parser {
    public Command parse(String rawInput) {
        String input = rawInput == null ? "" : rawInput.trim().toLowerCase();

        switch (input) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        default:
            return new AddTaskCommand(input);
        }
    }
}
