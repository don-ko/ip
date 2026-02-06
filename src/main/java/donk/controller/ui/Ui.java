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
public class Ui {
    private static final String LOGO = """
              _____   ____  _   _ _  __
             |  __ \\ / __ \\| \\ | | |/ /
             | |  | | |  | |  \\| | ' /
             | |  | | |  | | . ` |  <
             | |__| | |__| | |\\  | . \\
             |_____/ \\____/|_| \\_|_|\\_\\

            """;

    /**
     * Runs the read-evaluate-print loop until an exit command is issued.
     */
    public void run() {
        printWelcomeMessage();

        Parser parser = new Parser();
        TaskList taskList = new TaskList();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("input: ");
            String input = scanner.nextLine();
            Command command = null;
            try {
                command = parser.parse(input);
                System.out.println(command.execute(taskList) + "\n");
            } catch (InvalidInputException | StorageException e) {
                System.out.println("AAAAAAAA " + e.getMessage() + "\n");
            }
            if (command == null) {
                continue;
            }
            if (command.isExit()) {
                break;
            }
        }
        printLogo();
    }

    /**
     * Prints the ASCII logo and greeting shown on startup.
     */
    private void printWelcomeMessage() {
        printLogo();
        String welcome = "hiiii! what can i do for you today?";
        System.out.println(welcome);
    }

    /**
     * Prints the ASCII art logo.
     */
    private void printLogo() {
        System.out.println(LOGO);
    }
}
