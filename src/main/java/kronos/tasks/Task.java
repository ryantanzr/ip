package kronos.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a generic task with a description and completion status.
 */
public class Task {

    private String description;
    private List<String> tags;
    private boolean isDone;

    /**
     * Instantiates a new task.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.tags = new ArrayList<>();
        this.isDone = false;
    }

    /**
     * Adds a tag to the task.
     * @param tag A short tag
     */
    public void addTags(String... tags) {
        for (String tag : tags) {
            this.tags.add(tag);
        }
    }

    /**
     * Removes a tag from the task.
     * @param tag The tag to remove.
     */
    public void removeTag(String tag) {
        this.tags.remove(tag);
    }

    /**
     * Clears all tags from the task.
     */
    public void clearTags() {
        this.tags.clear();
    }

    /**
     * Returns the list of tags associated with the task.
     * @return The list of tags.
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * Returns the description of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the completion status of the task.
     * @param isDone The completion status to set.
     */
    public void setCompletionStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the completion status of the task.
     * @return The completion status of the task.
     */
    public String getCompletionStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the string representation of the task.
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for (String tag : tags) {
            sb.append(tag).append(" ");
        }

        String output = String.format("[%s] %s ", getCompletionStatus(), description);
        output += sb.toString();

        return output;
    }

    /**
     * Returns a CSV representation of the task.
     * @return The CSV representation of the task.
     */
    public String toDataString() {

        StringBuilder sb = new StringBuilder();
        for (String tag : tags) {
            sb.append(tag).append(",");
        }

        String dataString = String.format("%s,%s", description, (isDone ? "1" : "0"));

        // Remove trailing comma
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
            dataString += "," + sb.toString();
        }

        return dataString;
    }

}
