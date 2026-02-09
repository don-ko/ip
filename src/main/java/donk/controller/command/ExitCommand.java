package donk.controller.command;

import donk.controller.ui.Donk;
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
        return "Bye!!!\n";
    }
}
