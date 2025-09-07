package kronos.commands;

import java.util.List;

import kronos.tasklist.TaskList;
import kronos.tasks.Task;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand implements Command {

    private static final String LIST_MESSAGE = "Sir, here are the tasks in your list:\n";

    /**
     * Lists out all tasks in the storage.
     * @param taskList The list of tasks to retrieve.
     * @return A formatted string listing all tasks.
     */
    @Override
    public String execute(TaskList taskList) {

        List<Task> tasks = taskList.getAllTasks();
        String storedText = LIST_MESSAGE;

        // Start a for loop to print out the stored text
        for (int index = 0; index < tasks.size(); ++index) {
            storedText += String.format("%d.", index + 1);
            storedText += tasks.get(index).toString() + "\n";
        }

        return storedText.substring(0, storedText.length() - 1);
    }

}
