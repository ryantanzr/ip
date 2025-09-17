package kronos.commands;

import java.util.Arrays;

import kronos.exceptions.KronosException;
import kronos.tasklist.TaskList;
import kronos.tasks.Task;

/**
 * Represents a command to mark a task as complete.
 */
public class TagCommand implements Command {

    private static final String TAG_PATTERN = "^#[a-zA-Z]+$";
    private static final int MAX_TAGS = 3;

    private final String request;
    private final int taskNumber;

    /**
     * Constructs a TagCommand.
     * @param request The full user input string.
     * @param taskNumber The index of the task to tag.
     */
    public TagCommand(String request, int taskNumber) {
        this.request = request;
        this.taskNumber = taskNumber;
    }

    /**
     * Validates the tags before adding them to the task.
     * @param currentTagCount The current number of tags on the task.
     * @param tags The array of tags to validate.
     * @throws KronosException If any tag is invalid.
     */
    private void validateTags(int currentTagCount, String... tags) throws KronosException {
        if (currentTagCount + tags.length > MAX_TAGS) {
            throw new KronosException("A task can have a maximum of " + MAX_TAGS + " tags!");
        }
        for (String tag : tags) {
            if (!tag.matches(TAG_PATTERN)) {
                throw new KronosException("Only alphanumeric characters and underscores are allowed!");
            }
        }
    }

    /**
     * Adds tags to a task.
     * @param taskList The list of tasks to modify.
     * @return A formatted string showing the new tags.
     * @throws KronosException If the task number is invalid or tags are invalid.
     */
    @Override
    public String execute(TaskList taskList) throws KronosException {

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
