package donk.controller.ui;

import donk.controller.command.Command;
import donk.controller.command.Parser;
import donk.model.exception.InvalidInputException;
import donk.model.exception.StorageException;
import donk.model.task.TaskList;

/**
 * Handles the interactive console loop for the application.
 */
public class Donk {
    public static final String LOGO = """
              _____   ____  _   _ _  __
             |  __ \\ / __ \\| \\ | | |/ /
             | |  | | |  | |  \\| | ' /
             | |  | | |  | | . ` |  <
             | |__| | |__| | |\\  | . \\
             |_____/ \\____/|_| \\_|_|\\_\\

            """;
    private final Parser parser = new Parser();
    private final TaskList taskList = new TaskList();
    /**
    * Parses the given user input into a command and executes it against the task list\.
    *
    * @param userInput the raw input entered by the user
    * @return the command result string, always terminated with a newline */
    public String getResponse(String userInput) {
        Command command = null;
        try {
            command = parser.parse(userInput);
            return command.execute(taskList) + "\n";
        } catch (InvalidInputException | StorageException e) {
            return "AAAAAAAA " + e.getMessage() + "\n";
        }
    }
}
