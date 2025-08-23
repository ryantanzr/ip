package kronos.commands;

import kronos.tasklist.TaskList;
import kronos.tasks.Task;


public class DeleteCommand implements Command {

    private final int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes a task from the storage
     * @param taskNumber he index of the task to be deleted
     * @return A formatted string showing the removed task
     * @throws IndexOutOfBoundsException If the task number is invalid
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
