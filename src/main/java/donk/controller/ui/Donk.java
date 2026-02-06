package donk.controller.ui;

import java.util.Scanner;

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
