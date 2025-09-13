package kronos.commands;

import java.util.Arrays;

import kronos.tasklist.TaskList;
import kronos.tasks.Task;

/**
 * Represents a command to mark a task as complete.
 */
public class TagCommand implements Command {

    private final String request;
    private final int taskNumber;
    private static final int MAX_TAGS=3;
    private static final String TAG_PATTERN = "^#[a-zA-Z]+$";

    public TagCommand(String request, int taskNumber) {
        this.request = request;
        this.taskNumber = taskNumber;
    }

    /**
     * Validate the tags
     * @param tags The array of tags to validate
     * @throws IllegalArgumentException If any tag is invalid
     */
    private void validateTags(int currentTagCount, String... tags) throws IllegalArgumentException {
        if (currentTagCount + tags.length > MAX_TAGS) {
            throw new IllegalArgumentException("Sir, a task can have a maximum of " + MAX_TAGS + " tags.");
        }
        for (String tag : tags) {
            if (!tag.matches(TAG_PATTERN)) {
                throw new IllegalArgumentException("Tag '" + tag + "' contains invalid characters. Only alphanumeric characters and underscores are allowed.");
            }
        }
    }

    /**
     * Adds tags to a task.
     * @param taskList The list of tasks to modify.
     * @return A formatted string showing the new tags
     */
    @Override
    public String execute(TaskList taskList) {

        String responseString = "Tagged the task with: ";
        int extractionStartIndex = 2;

        // Extract a subarray of tags from the request
        String[] components = request.split(" ");
        String[] tags = Arrays.copyOfRange(components, extractionStartIndex, components.length);
        Task task = taskList.getTask(taskNumber);

        validateTags(task.getTags().size(), tags);

        // Add the tags to the task
        task.addTags(tags);

        // Append to response string
        for (String tag : tags) {
            responseString += tag + " ";
        }

        // Return a formatted string showing the new status and description
        return responseString;

    }
}
