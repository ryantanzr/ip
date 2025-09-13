package kronos.commands;

import java.util.Arrays;

import kronos.tasklist.TaskList;
import kronos.tasks.Task;

/**
 * Represents a command to mark a task as complete.
 */
public class UntagCommand implements Command {

    private final String request;
    private final int taskNumber;
    private static final String ALL_KEYWORD = "#ALL";

    public UntagCommand(String request, int taskNumber) {
        this.request = request;
        this.taskNumber = taskNumber;
    }

    /**
     * Removes 1 or all tags from a task.
     * @param taskList The list of tasks to modify.
     * @return A formatted string showing the new tags
     */
    @Override
    public String execute(TaskList taskList) {

        String responseString = "";

        Task task = taskList.getTask(taskNumber);

        if (request.equals(ALL_KEYWORD)) {
            task.clearTags();
            responseString = "Removed all tags from the task.";
        } else {
            boolean isRemoved = task.removeTag(request);
            if (!isRemoved) {
                throw new IllegalArgumentException("The tag '" + request + "' does not exist on this task.");
            } else {
                responseString = "Removed the tag: " + request + " from the task.";
            }
        }

        return responseString;

    }
}
