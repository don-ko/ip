package donk;

import donk.controller.command.Command;
import donk.controller.command.Parser;
import donk.model.exceptions.InvalidInputException;
import donk.model.task.TaskList;

import java.util.Scanner;

public class Donk {
    public void run() {
        printWelcomeMessage();

        Parser parser = new Parser();
        TaskList taskList = new TaskList();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Input: ");
            String input = scanner.nextLine();
            Command command = null;
            try {
                command = parser.parse(input);
                System.out.println(command.execute(taskList));
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage() + "\n");
            }
            if (command == null) { continue; }
            if (command.isExit()) { break; }
        }
    }

    private void printWelcomeMessage() {
        String logo = "  _____   ____  _   _ _  __\n" +
                " |  __ \\ / __ \\| \\ | | |/ /\n" +
                " | |  | | |  | |  \\| | ' / \n" +
                " | |  | | |  | | . ` |  <  \n" +
                " | |__| | |__| | |\\  | . \\ \n" +
                " |_____/ \\____/|_| \\_|_|\\_\\\n\n";
        String welcome = "Hello! What can I do for you today?";
        System.out.println(logo + welcome);
    }
}
