package kronos.commands;

import java.util.ArrayList;
import java.util.List;

import kronos.tasklist.TaskList;
import kronos.tasks.Task;

/**
 * Represents a command to find tasks containing a specific search term.
 */
public class FindCommand implements Command {

    private final String searchTerm;

    /**
     * Constructs a FindCommand.
     * @param searchTerm The term to search for.
     */
    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * Executes the find command.
     * @param taskList The task list to search.
     */
    public String execute(TaskList taskList) {

        List<Task> matches = new ArrayList<>();
        String responseString = "";

        // Do an O(n) search to extract all matching tasks
        for (Task task : taskList.getAllTasks()) {
            if (task.getDescription().contains(searchTerm)) {
                matches.add(task);
            }
        }

        if (matches.isEmpty()) {
            responseString = "Sir, there are no matching tasks...\n";
        } else {
            responseString = "Sir, here are the matches I've found:\n";
            // Start a for loop to print out the stored text
            for (int index = 0; index < matches.size(); ++index) {
                responseString += String.format("%d.", index + 1);
                responseString += matches.get(index).toString() + "\n";
            }
            responseString = responseString.substring(0, responseString.length() - 1);
        }

        return responseString;
    }

}
