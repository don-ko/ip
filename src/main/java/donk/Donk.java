package donk;

import donk.model.task.Task;
import donk.model.task.TaskList;

import java.util.Scanner;

public class Donk {
    public void run() {
        printWelcomeMessage();

        TaskList taskList = new TaskList();
        Scanner scanner = new Scanner(System.in);

        boolean isExited = false;
        while (!isExited) {
            System.out.print("Input: ");
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(taskList);
            } else {
                taskList.add(new Task(input));
                System.out.println("added: " + input + "\n");
            }
        }

        exit();
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

    private void exit() {
        System.out.println("Bye!!!!");
    }
}
