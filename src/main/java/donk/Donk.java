package donk;

import donk.controller.Command;
import donk.model.command.Parser;
import donk.model.task.Task;
import donk.model.task.TaskList;

import java.util.Scanner;

public class Donk {
    public void run() {
        printWelcomeMessage();

        Parser parser = new Parser();
        TaskList taskList = new TaskList();
        Scanner scanner = new Scanner(System.in);

        boolean isExited = false;
        while (!isExited) {
            System.out.print("Input: ");
            String input = scanner.nextLine();
            Command command = parser.parse(input);
            System.out.println(command.execute(taskList));
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
