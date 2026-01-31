package donk.controller.ui;

import donk.controller.command.Command;
import donk.controller.command.Parser;
import donk.model.exception.InvalidInputException;
import donk.model.exception.StorageException;
import donk.model.task.TaskList;

import java.util.Scanner;

public class Ui {
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
            if (command == null) { continue; }
            if (command.isExit()) { break; }
        }
    }

    private void printWelcomeMessage() {
        String logo = "  _____   ____  _   _ _  __\n" +
                " |  __ \\ / __ \\| \\ | | |/ /\n" +
                " | |  | | |  | |  \\| | ' /\n" +
                " | |  | | |  | | . ` |  <\n" +
                " | |__| | |__| | |\\  | . \\\n" +
                " |_____/ \\____/|_| \\_|_|\\_\\\n\n";
        String welcome = "hiiii! what can i do for you today?";
        System.out.println(logo + welcome);
    }
}
