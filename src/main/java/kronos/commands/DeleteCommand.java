package kronos.commands;

import kronos.tasklist.TaskList;
import kronos.tasks.Task;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand implements Command {

    private final int taskNumber;

    /**
     * Constructs a DeleteCommand.
     * @param taskNumber The index of the task to delete.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes a task from the storage.
     * @param taskList The list of tasks to modify.
     * @return A formatted string showing the removed task.
     * @throws IndexOutOfBoundsException If the task number is invalid.
     */
    @Override
    public String execute(TaskList taskList) throws IndexOutOfBoundsException {


        // Check if the task number is valid
        if (taskNumber < 0 || taskNumber >= taskList.getAllTasks().size()) {
            throw new IndexOutOfBoundsException("Invalid task number: " + (taskNumber + 1));
        }

        // Remove the task from the list
        Task removedTask = taskList.removeTask(taskNumber);

        // Format the response to show the removed task
        String responseString = "Noted. I've removed this task:\n";
        responseString += "  " + removedTask.toString() + "\n"
            + "Now you have " + taskList.getAllTasks().size() + " tasks in the list.";

        return responseString;
    }
}
