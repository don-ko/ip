package donk.controller.command;

import donk.model.task.TaskList;

/**
 * Exits the application.
 */
public class ExitCommand extends Command {

    /**
     * Returns the farewell message.
     *
     * @param tasks task list (unused)
     * @return farewell message
     */
    @Override
    public String execute(TaskList tasks) {
        return "Bye!!!\n"
                + "  _____   ____  _   _ _  __\n"
                + " |  __ \\ / __ \\| \\ | | |/ /\n"
                + " | |  | | |  | |  \\| | ' /\n"
                + " | |  | | |  | | . ` |  <\n"
                + " | |__| | |__| | |\\  | . \\\n"
                + " |_____/ \\____/|_| \\_|_|\\_\\";
    }

    /**
     * Signals that the UI loop should terminate.
     *
     * @return true to exit the application
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
