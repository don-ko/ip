package donk.controller.command;

import donk.model.task.TaskList;

public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks) {
        return "Bye!!!\n" +
                "  _____   ____  _   _ _  __\n" +
                " |  __ \\ / __ \\| \\ | | |/ /\n" +
                " | |  | | |  | |  \\| | ' / \n" +
                " | |  | | |  | | . ` |  <  \n" +
                " | |__| | |__| | |\\  | . \\ \n" +
                " |_____/ \\____/|_| \\_|_|\\_\\";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
