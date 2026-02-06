package donk.controller.command;

import donk.model.task.Task;
import donk.model.task.TaskList;

/**
 * Finds tasks in a {@link TaskList} whose description matches the given search text.
 */
public class FindCommand extends Command {
    /**
     * Raw search text provided by the user.
     */
    private final String searchString;

    /**
     * Creates a command that searches for tasks containing the given text.
     *
     * @param searchString user-provided text to search for
     */
    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    /**
     * Executes the search and formats the matching tasks using 1-based indices.
     *
     * @param tasks task list to search
     * @return formatted search result message
     */
    @Override
    public String execute(TaskList tasks) {
        Task[] matchingTasks = tasks.searchTasks(searchString.toLowerCase().trim());
        if (matchingTasks.length == 0) {
            return "no matching tasks found :(";
        }

        StringBuilder sb = new StringBuilder("found matching tasks!\n");
        for (int i = 1; i <= matchingTasks.length; i++) {
            sb.append(i).append(".").append(matchingTasks[i - 1]).append("\n");
        }

        return sb.toString().trim();

    }
}
