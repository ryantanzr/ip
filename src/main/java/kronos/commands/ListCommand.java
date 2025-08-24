package kronos.commands;

import java.util.List;

import kronos.tasklist.TaskList;
import kronos.tasks.Task;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand implements Command {

    /**
     * Executes the list command.
     * @param taskList The list of tasks to retrieve.
     * @return A formatted string listing all tasks.
     */
    @Override
    public String execute(TaskList taskList) {

        List<Task> tasks = taskList.getAllTasks();
        String listMessage = "Here are the tasks I've helped you store: \n";
        String storedText = listMessage;

        // Start a for loop to print out the stored text
        for (int index = 0; index < tasks.size(); ++index) {
            storedText += String.format("%d.", index + 1);
            storedText += tasks.get(index).toString() + "\n";
        }

        return storedText.substring(0, storedText.length() - 1);
    }

}
