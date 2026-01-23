package donk;

import java.util.Scanner;

public class Donk {
    public void run() {
        printWelcomeMessage();

        String[] tasks = new String[100];
        int currTask = 0;
        Scanner scanner = new Scanner(System.in);

        boolean isExited = false;
        while (!isExited) {
            System.out.print("Input: ");
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                for (int i = 0; tasks[i] != null; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else {
                tasks[currTask++] = input;
                System.out.println("added: " + input);
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
