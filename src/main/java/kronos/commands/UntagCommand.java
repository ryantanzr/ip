package kronos.commands;

import kronos.exceptions.KronosException;
import kronos.tasklist.TaskList;
import kronos.tasks.Task;

/**
 * Represents a command to mark a task as complete.
 */
public class UntagCommand implements Command {

    private static final String ALL_KEYWORD = "#ALL";

    private final String request;
    private final int taskNumber;

    /**
     * Constructs an UntagCommand.
     * @param request The full user input string.
     * @param taskNumber The index of the task to untag.
     */
    public UntagCommand(String request, int taskNumber) {
        this.request = request;
        this.taskNumber = taskNumber;
    }

    /**
     * Removes 1 or all tags from a task.
     * @param taskList The list of tasks to modify.
     * @return A formatted string showing the new tags.
     */
    @Override
    public String execute(TaskList taskList) throws KronosException {

        if (taskNumber < 0 || taskNumber >= taskList.getAllTasks().size()) {
            throw new KronosException("That task number is invalid!");
        }

        String responseString = "";

        Task task = taskList.getTask(taskNumber);

        if (request.equals(ALL_KEYWORD)) {
            task.clearTags();
            responseString = "Removed all tags from the task.";
        } else {
            boolean isRemoved = task.removeTag(request);
            if (!isRemoved) {
                throw new KronosException("The tag '" + request + "' does not exist!");
            } else {
                responseString = "Removed the tag: " + request + " from the task.";
            }
        }

        return responseString;

    }
}
