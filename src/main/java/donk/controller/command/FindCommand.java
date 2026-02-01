package donk.controller.command;

import donk.model.task.Task;
import donk.model.task.TaskList;

public class FindCommand extends Command {
    private final String searchString;

    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public String execute(TaskList tasks) {
        StringBuilder sb = new StringBuilder("found matching tasks!\n");

        Task[] matchingTasks = tasks.searchTasks(searchString.toLowerCase().trim());
        if (matchingTasks.length == 0) {
            return "no matching tasks found :(";
        }
        for (int i = 1; i <= matchingTasks.length; i++) {
            sb.append(i).append(".").append(matchingTasks[i - 1]).append("\n");
        }

        return sb.toString().trim();

    }
}
